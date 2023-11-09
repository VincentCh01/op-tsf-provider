package com.zhiyunheyi.aibot.operate.repository;

import com.zhiyunheyi.aibot.operate.core.RoleMenu;

import java.util.List;

public interface IRoleMenuRepository {
    int insert(List<RoleMenu> roleMenuList);

    int delete(List<String> ids);

    int deleteByRoleId(List<Long> roleIds);

    int deleteByMenuId(List<Long> menuIds);

    int deleteByRoleAndRes(String roleId, List<String> menuIds);

    List<RoleMenu> query(RoleMenu roleRes);

    List<RoleMenu> queryWithCreatedBy(RoleMenu roleRes, Long createdBy);

    List<RoleMenu> selectByRoleId(Long roleId);

    List<RoleMenu> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy);
}
