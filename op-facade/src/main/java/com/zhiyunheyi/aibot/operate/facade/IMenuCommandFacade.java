package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @menu:
 * @ClassName: IMenuCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:30
 * @Version: 1.0.0-SNAPSHOT
 */
public interface IMenuCommandFacade {
    @PostMapping("/insert")
    ApiResult<Boolean> insert(@RequestBody List<MenuDTO> dtoList);

    @PostMapping("/update")
    ApiResult<Boolean> update(@RequestBody MenuDTO dto);

    @PostMapping("/delete")
    ApiResult<Boolean> delete(@RequestBody List<Long> ids);
}
