package com.zhiyunheyi.aibot.operate.facade;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @menu:
 * @ClassName: IRoleCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:31
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/role")
public interface IRoleCommandFacade {
    @PostMapping("/insert")
    ApiResult<Boolean> insert(@RequestBody List<RoleDTO> dtoList);

    @PostMapping("/update")
    ApiResult<Boolean> update(@RequestBody RoleDTO dto);

    @PostMapping("/delete")
    ApiResult<Boolean> delete(@RequestBody List<Long> ids);
}
