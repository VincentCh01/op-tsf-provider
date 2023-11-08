package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IAccountQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.Account;
import com.zhiyunheyi.aibot.user.domain.dto.AccountDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.AccountCondition;
import com.zhiyunheyi.aibot.userservice.operate.service.AccountService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: AccountQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:26
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class AccountQueryFacade implements IAccountQueryFacade {

    @Resource
    private AccountService service;

    @Override
    @SneakyThrows
    public ApiResult<List<AccountDTO>> select(@RequestParam List<Long> ids) {
        return ApiResult.ok(AssembleUtil.listTo(this.service.select(ids), AccountDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<AccountDTO>> page(AccountCondition condition, int pageNo, int pageSize) {
        PageResponse<Account> page = this.service.page(condition, pageNo, pageSize);
        List<AccountDTO> dtoList = AssembleUtil.listTo(page.getList(), AccountDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
