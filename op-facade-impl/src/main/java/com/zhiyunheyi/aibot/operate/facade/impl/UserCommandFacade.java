package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.facade.IUserCommandFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.UserDTO;
import com.zhiyunheyi.aibot.operate.service.IUserService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: UserCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:26
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class UserCommandFacade implements IUserCommandFacade {

    @Resource
    private IUserService service;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insert(@RequestBody List<UserDTO> dtoList) {
        this.service.insert(AssembleUtil.listTo(dtoList, User.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> update(@RequestBody UserDTO dto) {
        this.service.update(AssembleUtil.to(dto, User.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.service.delete(ids);
        return ApiResult.ok(true);
    }
}
