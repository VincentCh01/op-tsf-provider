package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.facade.IUserQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.UserDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.UserCondition;
import com.zhiyunheyi.aibot.operate.service.IUserService;
import com.zhiyunheyi.aibot.operate.vo.UserConditionVO;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: UserQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:27
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class UserQueryFacade implements IUserQueryFacade {

    @Resource
    private IUserService service;

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<UserDTO>> page(UserCondition condition, int pageNo, int pageSize) {
        PageResponse<User> page = this.service.page(AssembleUtil.to(condition, UserConditionVO.class), pageNo, pageSize);
        List<UserDTO> dtoList = AssembleUtil.listTo(page.getList(), UserDTO.class);
        return ApiResult.ok(new PageResponse<>(pageNo, pageSize, page.getTotal(), dtoList));
    }
}
