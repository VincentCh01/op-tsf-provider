package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IRoleResQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.RoleRes;
import com.zhiyunheyi.aibot.user.domain.dto.RoleResDTO;
import com.zhiyunheyi.aibot.userservice.operate.service.RoleResService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
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
    private RoleResService service;

    @Override
    @SneakyThrows
    public ApiResult<List<RoleResDTO>> query(RoleResDTO dto) {
        return ApiResult.ok(AssembleUtil.listTo(this.service.query(AssembleUtil.to(dto, RoleRes.class)), RoleResDTO.class));
    }
}
