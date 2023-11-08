package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IMenuQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.Menu;
import com.zhiyunheyi.aibot.user.domain.dto.MenuDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.MenuCondition;
import com.zhiyunheyi.aibot.userservice.operate.service.MenuService;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: MenuQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:39
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/menu")
@RestController
public class MenuQueryFacade implements IMenuQueryFacade {

    @Resource(name = "myMenuService")
    private MenuService service;

    @SneakyThrows
    @GetMapping("/page")
    public ApiResult<PageResponse<MenuDTO>> page(MenuCondition condition, int pageNo, int pageSize) {
        PageResponse<Menu> page = this.service.page(condition, pageNo, pageSize);
        List<MenuDTO> dtoList = AssembleUtil.listTo(page.getList(), MenuDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
