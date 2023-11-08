package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IResourceQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.Resource;
import com.zhiyunheyi.aibot.user.domain.dto.ResourceDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.ResourceCondition;
import com.zhiyunheyi.aibot.userservice.operate.service.ResourceService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @menu:
 * @ClassName: ResourceQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:28
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/resource")
@RestController
public class ResourceQueryFacade implements IResourceQueryFacade {

    @javax.annotation.Resource
    private ResourceService service;

    @SneakyThrows
    @GetMapping("/page")
    public ApiResult<PageResponse<ResourceDTO>> page(ResourceCondition condition, int pageNo, int pageSize) {
        PageResponse<Resource> page = this.service.page(condition, pageNo, pageSize);
        List<ResourceDTO> dtoList = AssembleUtil.listTo(page.getList(), ResourceDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
