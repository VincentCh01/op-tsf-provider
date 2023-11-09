package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.facade.IRoleQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCondition;
import com.zhiyunheyi.aibot.operate.service.IRoleService;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import lombok.SneakyThrows;
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

    @Resource
    private IRoleService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition, int pageNo, int pageSize) {
        PageResponse<Role> page = this.service.page(AssembleUtil.to(condition, RoleConditionVO.class), pageNo, pageSize);
        List<RoleDTO> dtoList = AssembleUtil.listTo(page.getList(), RoleDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
