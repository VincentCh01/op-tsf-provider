package com.zhiyunheyi.aibot.operate.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.zhiyunheyi.aibot.domain.core.ApiCode;
import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.exception.AuthorizeException;
import com.zhiyunheyi.aibot.operate.facade.IUcenterQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.response.UserInfo;
import com.zhiyunheyi.aibot.operate.utils.AESUtil;
import com.zhiyunheyi.aibot.operate.utils.RedisUtil;
import com.zhiyunheyi.aibot.operate.vo.AuthTokenVO;
import com.zhiyunheyi.aibot.operate.vo.UserContext;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @author wangyong
 * @description 运营后台身份登录拦截
 * @create 2023-06-28 18:33
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final static String COOKIE_KEY = "o-token";

    /**
     * 连接符
     */
    private final static String SPLITTER_SEC = "\001";

    /**
     * aes密钥
     */
    private static final String AES_KEY = "polkmn0987654321";

    /**
     * salt
     */
    private static final String AUTH_SALT = "R0BoT";

    private static final String REDIS_TOKEN_KEY = "AIBOT:O:UAERTOKEN:%s";

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private IUcenterQueryFacade ucenterQueryFacade;

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Optional<Cookie> cookie = Arrays.asList(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .stream()
                .filter(c -> c != null && StringUtils.equals(c.getName(), COOKIE_KEY))
                .findAny();
        String token = cookie.isPresent() ? cookie.get().getValue() : request.getHeader(COOKIE_KEY);
        if (!this.authenticate(token, request)) {
            log.error("用户登录token验证不正确，token:{}", token);
            throw new AuthorizeException(ResultEnum.NO_LOGIN);
        }

        AuthTokenVO user = UserContext.getUser();
        if (CollectionUtils.isEmpty(user.getRoleIds())) {
            log.warn("token 无效 {} 或 用户权限不正确", token);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "用户权限不正确");

            return false;
        }

        ApiResult<List<ResourceDTO>> result = this.ucenterQueryFacade.selectByRoleId(Long.valueOf(user.getRoleIds().get(0)));
        if (result == null || !result.isSuccess() || CollectionUtils.isEmpty(result.getData())) {
            return false;
        }

        List<ResourceDTO> resourceDTOList = result.getData();
        for (ResourceDTO resourceDTO : resourceDTOList) {
            if (resourceDTO.getUrl().equals(request.getRequestURI())) {
                return true;
            }
        }

        log.warn("token 无效 {} 或 用户权限不正确", token);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "用户权限不正确");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private boolean authenticate(String token, HttpServletRequest request) {
        if (StringUtils.isBlank(token)) {
            log.error("OperateAuthInterceptor 未获取到token");
            throw new AuthorizeException(ResultEnum.NO_LOGIN);
        }

        return Try.of(() -> {
                    AuthTokenVO tokenDTO = this.decompressLv2(token);
                    Preconditions.checkArgument(
                            tokenDTO != null
                                    && StringUtils.equals(tokenDTO.getSalt(), AUTH_SALT)
                                    && tokenDTO.getTimestamp() > System.currentTimeMillis()
                            , String.format("token明文异常: %s", JSON.toJSONString(tokenDTO)));

                    Preconditions.checkArgument(token.equals(redisUtil.get(String.format(REDIS_TOKEN_KEY, tokenDTO.getUserId()))));
                    tokenDTO.setToken(token);
                    UserContext.setUser(tokenDTO);

                    ApiResult<UserInfo> apiResult = this.ucenterQueryFacade.getByMobile(tokenDTO.getPhone());
                    UserInfo userInfo = apiResult.getData();
                    if (apiResult == null || apiResult.getCode() != ApiCode.SUCCESS.getCode() || userInfo == null) {
                        return Boolean.TRUE;
                    }

                    tokenDTO.setPhone(userInfo.getMobile());
                    tokenDTO.setRoleIds(Lists.newArrayList(userInfo.getRoleId()));
                    return Boolean.TRUE;
                })
                .onFailure(t -> log.error("身份验证失败 msg: {} token: {}", t.getMessage(), token, t))
                .getOrElse(Boolean.FALSE);
    }

    @SneakyThrows
    private AuthTokenVO decompressLv2(String lv1Token) {
        String decryptToken = AESUtil.decryptStrHex(lv1Token, AES_KEY);
        String[] split = decryptToken.split(SPLITTER_SEC);
        AuthTokenVO authTokenDTO = new AuthTokenVO();
        authTokenDTO.setUserId(Try.of(() -> Long.parseLong(split[0]))
                .onFailure(t -> log.error("decompress error userId lv1Token={}", lv1Token, t))
                .getOrElse(0L));
        authTokenDTO.setType(Try.of(() -> split[1])
                .onFailure(t -> log.error("decompress error type lv1Token={}", lv1Token, t))
                .getOrElse(""));
        authTokenDTO.setSalt(Try.of(() -> split[2])
                .onFailure(t -> log.error("decompress error salt lv1Token={}", lv1Token, t))
                .getOrElse(""));
        authTokenDTO.setMerchantId(Try.of(() -> Long.parseLong(split[3]))
                .onFailure(t -> log.error("decompress error merchantId lv1Token={}", lv1Token, t))
                .getOrElse(0L));
        authTokenDTO.setPhone(Try.of(() -> split[4])
                .onFailure(t -> log.error("decompress error phone lv1Token={}", lv1Token, t))
                .getOrElse(""));
        authTokenDTO.setTimestamp(Try.of(() -> Long.parseLong(split[5]))
                .onFailure(t -> log.error("decompress error timestamp lv1Token={}", lv1Token, t))
                .getOrElse(0L));

        return authTokenDTO;
    }
}
