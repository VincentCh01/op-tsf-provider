package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.RoleMenu;
import com.zhiyunheyi.aibot.operate.facade.IRoleMenuQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleMenuDTO;
import com.zhiyunheyi.aibot.operate.service.IRoleMenuService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleMenuQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:39
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class RoleMenuQueryFacade implements IRoleMenuQueryFacade {
    @Resource
    private IRoleMenuService service;

    @Override
    @SneakyThrows
    public ApiResult<List<RoleMenuDTO>> query(RoleMenuDTO dto) {
        return ApiResult.ok(AssembleUtil.listTo(this.service.query(AssembleUtil.to(dto, RoleMenu.class)), RoleMenuDTO.class));
    }
}
