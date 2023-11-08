package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IUserQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.User;
import com.zhiyunheyi.aibot.user.domain.dto.UserDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.UserCondition;
import com.zhiyunheyi.aibot.userservice.operate.service.UserService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: UserQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:27
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class UserQueryFacade implements IUserQueryFacade {

    @Resource
    private UserService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<UserDTO>> page(UserCondition condition, int pageNo, int pageSize) {
        PageResponse<User> page = this.service.page(condition, pageNo, pageSize);
        List<UserDTO> dtoList = AssembleUtil.listTo(page.getList(), UserDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
