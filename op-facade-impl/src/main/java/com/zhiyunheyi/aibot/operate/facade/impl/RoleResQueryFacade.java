package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.RoleRes;
import com.zhiyunheyi.aibot.operate.facade.IRoleResQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleResDTO;
import com.zhiyunheyi.aibot.operate.service.IRoleResService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleResQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 14:39
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class RoleResQueryFacade implements IRoleResQueryFacade {

    @Resource
    private IRoleResService service;

    @Override
    @SneakyThrows
    public ApiResult<List<RoleResDTO>> query(RoleResDTO dto) {
        return ApiResult.ok(AssembleUtil.listTo(this.service.query(AssembleUtil.to(dto, RoleRes.class)), RoleResDTO.class));
    }
}
