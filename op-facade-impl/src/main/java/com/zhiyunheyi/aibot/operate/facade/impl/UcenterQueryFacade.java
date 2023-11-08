package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IUcenterQueryFacade;
import com.zhiyunheyi.aibot.tsf.api.common.ApiResult;
import com.zhiyunheyi.aibot.tsf.api.common.PageResponse;
import com.zhiyunheyi.aibot.user.domain.dto.MenuDTO;
import com.zhiyunheyi.aibot.user.domain.dto.ResourceDTO;
import com.zhiyunheyi.aibot.user.domain.dto.RoleDTO;
import com.zhiyunheyi.aibot.user.domain.dto.request.AccountCondition;
import com.zhiyunheyi.aibot.user.domain.dto.request.AccountQuery;
import com.zhiyunheyi.aibot.user.domain.dto.request.RoleCondition;
import com.zhiyunheyi.aibot.user.domain.dto.response.MenuTree;
import com.zhiyunheyi.aibot.user.domain.dto.response.ResourceTree;
import com.zhiyunheyi.aibot.user.domain.dto.response.UserInfo;
import com.zhiyunheyi.aibot.userservice.operate.application.UcenterApplication;
import com.zhiyunheyi.aibot.userservice.operate.utils.AssembleUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: UcenterQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 14:44
 * @Version: 1.0.0-SNAPSHOT
 */

@RestController
public class UcenterQueryFacade implements IUcenterQueryFacade {

    @Resource
    private UcenterApplication app;

    @Override
    public ApiResult<UserInfo> getByUserId(Long userId) {
        return ApiResult.ok(this.app.getByUserId(userId));
    }

    @Override
    public ApiResult<UserInfo> getByMobile(String mobile) {
        return ApiResult.ok(this.app.getByMobile(mobile));
    }

    @Override
    public ApiResult<PageResponse<UserInfo>> page(AccountCondition condition,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ApiResult.ok(this.app.page(condition, pageNo, pageSize));
    }

    @Override
    public ApiResult<PageResponse<UserInfo>> pageByKey(AccountQuery condition,
                                                       @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ApiResult.ok(this.app.pageByKey(condition, pageNo, pageSize));
    }

    @Override
    public ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition,
                                                 @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ApiResult.ok(this.app.pageRole(condition, pageNo, pageSize));
    }

    @Override
    public ApiResult<List<RoleDTO>> selectAllRole() {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectAllRole(), RoleDTO.class));
    }

    @Override
    public ApiResult<List<ResourceTree>> treeAll() {
        return ApiResult.ok(this.app.treeAll());
    }

    @Override
    public ApiResult<List<ResourceTree>> treeByRoleId(Long roleId) {
        return ApiResult.ok(this.app.treeByRoleId(roleId));
    }

    @Override
    public ApiResult<List<ResourceDTO>> selectByRoleId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectByRoleId(roleId), ResourceDTO.class));
    }

    @Override
    public ApiResult<List<ResourceDTO>> selectByRoleIdAndUserId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectByRoleIdAndUserId(roleId), ResourceDTO.class));
    }

    @Override
    public ApiResult<List<ResourceDTO>> selectAllResource() {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectAllResource(), ResourceDTO.class));
    }

    @Override
    public ApiResult<List<MenuTree>> treeAllMenu() {
        return ApiResult.ok(this.app.treeAllMenu());
    }

    @Override
    public ApiResult<List<MenuTree>> treeMenuByRoleId(Long roleId) {
        return ApiResult.ok(this.app.treeMenuByRoleId(roleId));
    }

    @Override
    public ApiResult<List<MenuDTO>> selectMenuByRoleId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectMenuByRoleId(roleId), MenuDTO.class));
    }

    @Override
    public ApiResult<List<MenuDTO>> selectAllMenu() {
        return ApiResult.ok(AssembleUtil.listTo(this.app.selectAllMenu(), MenuDTO.class));
    }
}
