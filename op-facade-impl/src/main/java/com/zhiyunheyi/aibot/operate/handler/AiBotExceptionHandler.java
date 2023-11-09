package com.zhiyunheyi.aibot.operate.handler;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.operate.exception.AiBotException;
import com.zhiyunheyi.aibot.operate.exception.AuthorizeException;
import com.zhiyunheyi.aibot.operate.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
@Slf4j
public class AiBotExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult handler(HttpServletRequest request, Exception e) {
        String url = RequestUtil.getRequestUrl(request);
        log.info("-------请求url={}", url);
        if (e instanceof AiBotException) {
            AiBotException aiBotException = (AiBotException) e;
            log.error("【自定义异常】error={}, path={}", e, url);
            return ApiResult.fail(aiBotException.getMessage());
        } else if (e instanceof AuthorizeException) {
            AuthorizeException authorizeException = (AuthorizeException) e;
            log.error("【登录异常】error={}, path={}", e, url);
            return ApiResult.fail(authorizeException.getMessage());
        } else if (e instanceof AccountException) {
            AccountException accountException = (AccountException) e;
            log.error("【账号异常】error={}, path={}", e, url);
            return ApiResult.fail(accountException.getMessage());
        } else {
            e.printStackTrace();
            log.error("--------Exception.errorInfo>:{}", e);
            log.error("【其他异常】error={}, path={}", e, url);
            return ApiResult.fail(ResultEnum.SYSTEM_ERROR.getMessage());
        }
    }
}
