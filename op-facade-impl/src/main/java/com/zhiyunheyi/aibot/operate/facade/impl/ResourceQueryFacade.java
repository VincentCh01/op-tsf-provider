package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.facade.IResourceQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.ResourceCondition;
import com.zhiyunheyi.aibot.operate.service.IResourceService;
import com.zhiyunheyi.aibot.operate.vo.ResourceConditionVO;
import lombok.SneakyThrows;
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
    private IResourceService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<ResourceDTO>> page(ResourceCondition condition, int pageNo, int pageSize) {
        PageResponse<Resource> page = this.service.page(AssembleUtil.to(condition, ResourceConditionVO.class), pageNo, pageSize);
        List<ResourceDTO> dtoList = AssembleUtil.listTo(page.getList(), ResourceDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
