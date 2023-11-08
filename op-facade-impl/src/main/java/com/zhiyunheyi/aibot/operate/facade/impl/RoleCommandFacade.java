package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IRoleCommandFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.Role;
import com.zhiyunheyi.aibot.user.domain.dto.RoleDTO;
import com.zhiyunheyi.aibot.userservice.operate.service.MyRoleService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Resource(name = "myRoleService")
    private MyRoleService service;

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
