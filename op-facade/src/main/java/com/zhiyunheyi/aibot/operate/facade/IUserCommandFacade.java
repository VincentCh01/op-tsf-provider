package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.operate.facade.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @menu:
 * @ClassName: IUserCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:33
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/user")
public interface IUserCommandFacade {
    @PostMapping("/insert")
    ApiResult<Boolean> insert(@RequestBody List<UserDTO> dtoList);

    @PostMapping("/update")
    ApiResult<Boolean> update(@RequestBody UserDTO dto);

    @PostMapping("/delete")
    ApiResult<Boolean> delete(@RequestBody List<Long> ids);
}
