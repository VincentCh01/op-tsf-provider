package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.facade.IMenuQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.MenuCondition;
import com.zhiyunheyi.aibot.operate.service.IMenuService;
import com.zhiyunheyi.aibot.operate.vo.MenuConditionVO;
import lombok.SneakyThrows;
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
@RestController
public class MenuQueryFacade implements IMenuQueryFacade {

    @Resource
    private IMenuService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<MenuDTO>> page(MenuCondition condition, int pageNo, int pageSize) {
        PageResponse<Menu> page = this.service.page(AssembleUtil.to(condition, MenuConditionVO.class), pageNo, pageSize);
        List<MenuDTO> dtoList = AssembleUtil.listTo(page.getList(), MenuDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
