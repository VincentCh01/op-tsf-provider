package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.facade.IMenuCommandFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import com.zhiyunheyi.aibot.operate.service.IMenuService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: MenuCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:38
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/menu")
@RestController
public class MenuCommandFacade implements IMenuCommandFacade {

    @Resource
    private IMenuService service;

    @Override
    @SneakyThrows
    public ApiResult<Boolean> insert(@RequestBody List<MenuDTO> dtoList) {
        this.service.insert(AssembleUtil.listTo(dtoList, Menu.class));
        return ApiResult.ok(true);
    }

    @Override
    @SneakyThrows
    public ApiResult<Boolean> update(@RequestBody MenuDTO dto) {
        this.service.update(AssembleUtil.to(dto, Menu.class));
        return ApiResult.ok(true);
    }

    @Override
    public ApiResult<Boolean> delete(@RequestBody List<Long> ids) {
        this.service.delete(ids);
        return ApiResult.ok(true);
    }
}
