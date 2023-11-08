package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.operate.core.RoleMenu;

import java.util.List;

public interface IRoleMenuService {
    int insert(List<RoleMenu> roleMenuList);

    int delete(List<String> ids);

    int deleteByRoleId(List<Long> roleIds);

    int deleteByMenuId(List<Long> resIds);

    int deleteByRoleAndRes(String roleId, List<String> resIds);

    List<RoleMenu> query(RoleMenu roleMenu);

    List<RoleMenu> queryWithCreatedBy(RoleMenu roleMenu, Long createdBy);

    List<RoleMenu> selectByRoleId(Long roleId);

    List<RoleMenu> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy);
}
