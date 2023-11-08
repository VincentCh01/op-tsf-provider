package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleMenuDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @menu:
 * @ClassName: IRoleMenuQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:32
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/roleMenu")
public interface IRoleMenuQueryFacade {
    @GetMapping("/query")
    ApiResult<List<RoleMenuDTO>> query(RoleMenuDTO dto);
}
