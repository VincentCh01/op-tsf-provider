package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.facade.IRoleCommandFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleDTO;
import com.zhiyunheyi.aibot.operate.service.IRoleService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:27
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class RoleCommandFacade implements IRoleCommandFacade {

    @Resource
    private IRoleService service;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insert(@RequestBody List<RoleDTO> dtoList) {
        this.service.insert(AssembleUtil.listTo(dtoList, Role.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> update(@RequestBody RoleDTO dto) {
        this.service.update(AssembleUtil.to(dto, Role.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.service.delete(ids);
        return ApiResult.ok(true);
    }
}
