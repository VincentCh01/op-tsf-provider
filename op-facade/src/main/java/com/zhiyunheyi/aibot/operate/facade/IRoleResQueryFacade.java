package com.zhiyunheyi.aibot.operate.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @menu:
 * @ClassName: IRoleResQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:32
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/roleRes")
public interface IRoleResQueryFacade {
    @GetMapping("/query")
    ApiResult<List<RoleResDTO>> query(RoleResDTO dto);
}
