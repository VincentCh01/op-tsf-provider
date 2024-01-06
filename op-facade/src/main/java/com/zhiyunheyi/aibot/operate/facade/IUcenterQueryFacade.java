package com.zhiyunheyi.aibot.operate.facade;

import com.zhiyunheyi.aibot.domain.core.ApiResult;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.facade.dto.MenuDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.RoleDTO;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountCondition;
import com.zhiyunheyi.aibot.operate.facade.dto.request.AccountQuery;
import com.zhiyunheyi.aibot.operate.facade.dto.request.RoleCondition;
import com.zhiyunheyi.aibot.operate.facade.dto.response.MenuTree;
import com.zhiyunheyi.aibot.operate.facade.dto.response.ResourceTree;
import com.zhiyunheyi.aibot.operate.facade.dto.response.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
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
@FeignClient(name = "operate-user", contextId = "ucenterQuery")
@RequestMapping("/ucenter")
public interface IUcenterQueryFacade {
    @GetMapping("/userInfo/getByUserId")
    ApiResult<UserInfo> getByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/userInfo/getByMobile")
    ApiResult<UserInfo> getByMobile(@RequestParam("mobile") String mobile);

    @GetMapping("/userInfo/getByMobileWithoutAuth")
    ApiResult<UserInfo> getByMobileWithoutAuth(@RequestParam("mobile") String mobile);

    @GetMapping("/userInfo/page")
    ApiResult<PageResponse<UserInfo>> pageUserInfo(@SpringQueryMap AccountCondition condition,
                                           @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/userInfo/pageByKey")
    ApiResult<PageResponse<UserInfo>> pageUserInfoByKey(@SpringQueryMap AccountQuery condition,
                                                @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/role/page")
    ApiResult<PageResponse<RoleDTO>> pageRole(@SpringQueryMap RoleCondition condition,
                                          @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/role/selectAll")
    ApiResult<List<RoleDTO>> selectAllRole();

    @GetMapping("/resource/treeAll")
    ApiResult<List<ResourceTree>> treeAll();

    @GetMapping("/resource/treeByRoleId")
    ApiResult<List<ResourceTree>> treeByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectByRoleId")
    ApiResult<List<ResourceDTO>> selectByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectByRoleIdWithoutAuth")
    ApiResult<List<ResourceDTO>> selectByRoleIdWithoutAuth(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectByRoleIdAndUserId")
    ApiResult<List<ResourceDTO>> selectByRoleIdAndUserId(@RequestParam("roleId") Long roleId);

    @GetMapping("/resource/selectAll")
    ApiResult<List<ResourceDTO>> selectAllResource();

    @GetMapping("/menu/treeAll")
    ApiResult<List<MenuTree>> treeAllMenu();

    @GetMapping("/menu/treeByRoleId")
    ApiResult<List<MenuTree>> treeMenuByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/menu/selectByRoleId")
    ApiResult<List<MenuDTO>> selectMenuByRoleId(@RequestParam("roleId") Long roleId);

    @GetMapping("/menu/selectAll")
    ApiResult<List<MenuDTO>> selectAllMenu();
}
