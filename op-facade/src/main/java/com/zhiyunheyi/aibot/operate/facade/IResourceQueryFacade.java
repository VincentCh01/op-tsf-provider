package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.facade.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.ResourceCondition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @menu:
 * @ClassName: IResourceQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:31
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/resource")
public interface IResourceQueryFacade {
    @GetMapping("/page")
    ApiResult<PageResponse<ResourceDTO>> page(ResourceCondition condition, int pageNo, int pageSize);
}
