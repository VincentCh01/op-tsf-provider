package com.zhiyunheyi.aibot.operate.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @menu:
 * @ClassName: IUserQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:33
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/user")
public interface IUserQueryFacade {
    @GetMapping("/page")
    ApiResult<PageResponse<UserDTO>> page(UserCondition condition, int pageNo, int pageSize);
}
