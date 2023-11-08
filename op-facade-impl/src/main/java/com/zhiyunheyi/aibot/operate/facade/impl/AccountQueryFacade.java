package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.facade.IAccountQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.AccountDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountCondition;
import com.zhiyunheyi.aibot.operate.service.IAccountService;
import lombok.SneakyThrows;
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
    private IAccountService service;

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
