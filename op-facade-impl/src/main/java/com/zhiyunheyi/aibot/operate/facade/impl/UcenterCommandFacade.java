package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IUcenterCommandFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.Account;
import com.zhiyunheyi.aibot.user.domain.dto.AccountDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.RoleCreateReq;
import com.zhiyunheyi.aibot.userservice.operate.application.UcenterApplication;
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
 * @ClassName: UcenterCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 17:14
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class UcenterCommandFacade implements IUcenterCommandFacade {

    @Resource
    private UcenterApplication app;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insert(@RequestBody AccountDTO dto) {
        this.app.insert(AssembleUtil.to(dto, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> update(@RequestBody AccountDTO dto) {
        this.app.update(AssembleUtil.to(dto, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.app.delete(ids);
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> insert(@RequestBody RoleCreateReq req) {
        this.app.insertRole(req);
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> update(@RequestBody RoleCreateReq req) {
        this.app.updateRole(req);
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> deleteRole(@RequestBody List<Long> ids) {
        this.app.deleteRole(ids);
        return ApiResult.ok(true);
    }

}
