package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IRoleMenuQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.user.domain.RoleMenu;
import com.zhiyunheyi.aibot.user.domain.dto.RoleMenuDTO;
import com.zhiyunheyi.aibot.userservice.operate.service.RoleMenuService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/roleMenu")
@RestController
public class RoleMenuQueryFacade implements IRoleMenuQueryFacade {
    @Resource
    private RoleMenuService service;

    @SneakyThrows
    @GetMapping("/query")
    public ApiResult<List<RoleMenuDTO>> query(RoleMenuDTO dto) {
        return ApiResult.ok(AssembleUtil.listTo(this.service.query(AssembleUtil.to(dto, RoleMenu.class)), RoleMenuDTO.class));
    }
}
