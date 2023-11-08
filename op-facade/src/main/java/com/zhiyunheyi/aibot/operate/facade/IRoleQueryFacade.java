package com.zhiyunheyi.aibot.operate.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @menu:
 * @ClassName: IRoleQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:31
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/role")
public interface IRoleQueryFacade {
    @GetMapping("/page")
    ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition, int pageNo, int pageSize);
}
