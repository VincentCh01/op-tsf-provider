package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCondition;
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
