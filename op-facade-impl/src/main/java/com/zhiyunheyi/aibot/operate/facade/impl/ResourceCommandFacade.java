package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IResourceCommandFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.Resource;
import com.zhiyunheyi.aibot.user.domain.dto.ResourceDTO;
import com.zhiyunheyi.aibot.userservice.operate.service.ResourceService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @menu:
 * @ClassName: ResourceCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:27
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/resource")
@RestController
public class ResourceCommandFacade implements IResourceCommandFacade {

    @javax.annotation.Resource
    private ResourceService service;

    @SneakyThrows
    @PostMapping("/insert")
    public ApiResult<Boolean> insert(@RequestBody List<ResourceDTO> dtoList) {
        this.service.insert(AssembleUtil.listTo(dtoList, Resource.class));
        return ApiResult.ok(true);
    }

    @SneakyThrows
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody ResourceDTO dto) {
        this.service.update(AssembleUtil.to(dto, Resource.class));
        return ApiResult.ok(true);
    }

    @PostMapping("/delete")
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.service.delete(ids);
        return ApiResult.ok(true);
    }
}
