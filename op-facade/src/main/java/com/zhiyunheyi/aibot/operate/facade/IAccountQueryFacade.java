package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.facade.dto.AccountDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountCondition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @menu:
 * @ClassName: IAccountQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:30
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/account")
public interface IAccountQueryFacade {
    @GetMapping("/select")
    ApiResult<List<AccountDTO>> select(@RequestParam List<Long> ids);

    @GetMapping("/page")
    ApiResult<PageResponse<AccountDTO>> page(AccountCondition condition, int pageNo, int pageSize);
}
