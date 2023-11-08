package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.MenuCondition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @menu:
 * @ClassName: IMenuQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:31
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/menu")
public interface IMenuQueryFacade {
    @GetMapping("/page")
    ApiResult<PageResponse<MenuDTO>> page(MenuCondition condition, int pageNo, int pageSize);
}
