package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.operate.facade.dto.AccountDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCreateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @menu:
 * @ClassName: IUcenterCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:33
 * @Version: 1.0.0-SNAPSHOT
 */
@FeignClient(name = "operate-user", contextId = "ucenterCommand")
@RequestMapping("/ucenter")
public interface IUcenterCommandFacade {
    @PostMapping("/userInfo/insert")
    ApiResult<Boolean> insertAccount(@RequestBody AccountDTO dto);

    @PostMapping("/userInfo/update")
    ApiResult<Boolean> updateAccount(@RequestBody AccountDTO dto);

    @PostMapping("/userInfo/delete")
    ApiResult<Boolean> deleteAccount(@RequestBody List<Long> ids);

    @PostMapping("/role/insert")
    ApiResult<Boolean> insertRole(@RequestBody RoleCreateReq req);

    @PostMapping("/role/update")
    ApiResult<Boolean> updateRole(@RequestBody RoleCreateReq req);

    @PostMapping("/role/delete")
    ApiResult<Boolean> deleteRole(@RequestBody List<Long> ids);
}
