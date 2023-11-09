package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.application.IUcenterApplication;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.facade.IUcenterQueryFacade;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountCondition;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountQuery;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCondition;
import com.zhiyunheyi.aibot.operate.facade.dto.response.MenuTree;
import com.zhiyunheyi.aibot.operate.facade.dto.response.ResourceTree;
import com.zhiyunheyi.aibot.operate.facade.dto.response.UserInfo;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import com.zhiyunheyi.aibot.operate.vo.UserInfoVO;
import lombok.SneakyThrows;
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
    private IUcenterApplication application;

    @Override
    @SneakyThrows
    public ApiResult<UserInfo> getByUserId(Long userId) {
        UserInfoVO infoVO = this.application.getByUserId(userId);
        return ApiResult.ok(AssembleUtil.to(infoVO, UserInfo.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<UserInfo> getByMobile(String mobile) {
        UserInfoVO infoVO = this.application.getByMobile(mobile);
        return ApiResult.ok(AssembleUtil.to(infoVO, UserInfo.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<UserInfo>> page(AccountCondition condition,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponse<UserInfoVO> page = this.application.page(AssembleUtil.to(condition, AccountConditionVO.class), pageNo, pageSize);
        return ApiResult.ok(new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), AssembleUtil.listTo(page.getList(), UserInfo.class)));
    }

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<UserInfo>> pageByKey(AccountQuery condition,
                                                       @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponse<UserInfoVO> page = this.application.pageByKey(AssembleUtil.to(condition, AccountQueryVO.class), pageNo, pageSize);
        return ApiResult.ok(new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), AssembleUtil.listTo(page.getList(), UserInfo.class)));
    }

    @Override
    @SneakyThrows
    public ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition,
                                                 @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponse<Role> page = this.application.pageRole(AssembleUtil.to(condition, RoleConditionVO.class), pageNo, pageSize);
        return ApiResult.ok(new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), AssembleUtil.listTo(page.getList(), RoleDTO.class)));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<RoleDTO>> selectAllRole() {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectAllRole(), RoleDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<ResourceTree>> treeAll() {
        return ApiResult.ok(AssembleUtil.listTo(this.application.treeAll(), ResourceTree.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<ResourceTree>> treeByRoleId(Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.application.treeByRoleId(roleId), ResourceTree.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<ResourceDTO>> selectByRoleId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectByRoleId(roleId), ResourceDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<ResourceDTO>> selectByRoleIdAndUserId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectByRoleIdAndUserId(roleId), ResourceDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<ResourceDTO>> selectAllResource() {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectAllResource(), ResourceDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<MenuTree>> treeAllMenu() {
        return ApiResult.ok(AssembleUtil.listTo(this.application.treeAllMenu(), MenuTree.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<MenuTree>> treeMenuByRoleId(Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.application.treeMenuByRoleId(roleId), MenuTree.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<MenuDTO>> selectMenuByRoleId(@RequestParam("roleId") Long roleId) {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectMenuByRoleId(roleId), MenuDTO.class));
    }

    @Override
    @SneakyThrows
    public ApiResult<List<MenuDTO>> selectAllMenu() {
        return ApiResult.ok(AssembleUtil.listTo(this.application.selectAllMenu(), MenuDTO.class));
    }
}
