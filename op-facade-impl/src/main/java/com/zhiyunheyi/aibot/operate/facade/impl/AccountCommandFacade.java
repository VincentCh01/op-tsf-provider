package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IAccountCommandFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.Account;
import com.zhiyunheyi.aibot.user.domain.dto.AccountDTO;
import com.zhiyunheyi.aibot.userservice.operate.service.AccountService;
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
 * @ClassName: AccountCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:26
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/account")
@RestController
public class AccountCommandFacade implements IAccountCommandFacade {

    @Resource
    private AccountService service;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insert(@RequestBody List<AccountDTO> dtoList) {
        this.service.insert(AssembleUtil.listTo(dtoList, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> update(@RequestBody AccountDTO dto) {
        this.service.update(AssembleUtil.to(dto, Account.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.service.delete(ids);
        return ApiResult.ok(true);
    }

}
