package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IRoleQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.Role;
import com.zhiyunheyi.aibot.user.domain.dto.RoleDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.RoleCondition;
import com.zhiyunheyi.aibot.userservice.operate.service.MyRoleService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:27
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class RoleQueryFacade implements IRoleQueryFacade {

    @Resource(name = "myRoleService")
    private MyRoleService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition, int pageNo, int pageSize) {
        PageResponse<Role> page = this.service.page(condition, pageNo, pageSize);
        List<RoleDTO> dtoList = AssembleUtil.listTo(page.getList(), RoleDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
