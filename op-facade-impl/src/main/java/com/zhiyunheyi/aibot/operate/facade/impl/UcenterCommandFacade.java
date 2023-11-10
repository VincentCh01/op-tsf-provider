package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.application.IUcenterApplication;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.facade.IUcenterCommandFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.AccountDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCreateReq;
import com.zhiyunheyi.aibot.operate.vo.RoleCreateVO;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
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
    private IUcenterApplication application;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insertAccount(@RequestBody AccountDTO dto) {
        this.application.insert(AssembleUtil.to(dto, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> updateAccount(@RequestBody AccountDTO dto) {
        this.application.update(AssembleUtil.to(dto, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> deleteAccount(@RequestBody List<Long> ids) {
        this.application.delete(ids);
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insertRole(@RequestBody RoleCreateReq req) {
        this.application.insertRole(AssembleUtil.to(req, RoleCreateVO.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> updateRole(@RequestBody RoleCreateReq req) {
        this.application.updateRole(AssembleUtil.to(req, RoleCreateVO.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> deleteRole(@RequestBody List<Long> ids) {
        this.application.deleteRole(ids);
        return ApiResult.ok(true);
    }

}
