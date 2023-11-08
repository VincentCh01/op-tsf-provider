package com.zhiyunheyi.aibot.operate.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @menu:
 * @ClassName: IUcenterQueryFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/8 17:34
 * @Version: 1.0.0-SNAPSHOT
 */
@RequestMapping("/ucenter")
public interface IUcenterQueryFacade {
    @GetMapping("/userInfo/getByUserId")
    ApiResult<UserInfo> getByUserId(Long userId);

    @GetMapping("/userInfo/getByMobile")
    ApiResult<UserInfo> getByMobile(String mobile);

    @GetMapping("/userInfo/page")
    ApiResult<PageResponse<UserInfo>> page(AccountCondition condition,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/userInfo/pageByKey")
    ApiResult<PageResponse<UserInfo>> pageByKey(AccountQuery condition,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/role/page")
    ApiResult<PageResponse<RoleDTO>> page(RoleCondition condition,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/role/selectAll")
    ApiResult<List<RoleDTO>> selectAllRole();

    @GetMapping("/resource/treeAll")
    ApiResult<List<ResourceTree>> treeAll();

    @GetMapping("/resource/treeByRoleId")
    ApiResult<List<ResourceTree>> treeByRoleId(Long roleId);

    @GetMapping("/resource/selectByRoleId")
    ApiResult<List<ResourceDTO>> selectByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectByRoleIdAndUserId")
    ApiResult<List<ResourceDTO>> selectByRoleIdAndUserId(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectAll")
    ApiResult<List<ResourceDTO>> selectAllResource();

    @GetMapping("/menu/treeAll")
    ApiResult<List<MenuTree>> treeAllMenu();

    @GetMapping("/menu/treeByRoleId")
    ApiResult<List<MenuTree>> treeMenuByRoleId(Long roleId);

    @GetMapping("/menu/selectByRoleId")
    ApiResult<List<MenuDTO>> selectMenuByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/menu/selectAll")
    ApiResult<List<MenuDTO>> selectAllMenu();
}
