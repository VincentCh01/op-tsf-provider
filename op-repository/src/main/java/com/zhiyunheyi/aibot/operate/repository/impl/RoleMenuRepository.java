package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.operate.core.RoleMenu;
import com.zhiyunheyi.aibot.operate.repository.IRoleMenuRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.RoleMenuMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleMenuRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:36
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class RoleMenuRepository implements IRoleMenuRepository {

    @Resource(name = "myRoleMenuMapper")
    private RoleMenuMapper mapper;

    @Override
    public int insert(List<RoleMenu> roleMenuList) {
        return this.mapper.insert(roleMenuList);
    }

    @Override
    public int delete(List<String> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public int deleteByRoleId(List<Long> roleIds) {
        return this.mapper.deleteByRoleId(roleIds);
    }

    @Override
    public int deleteByMenuId(List<Long> menuIds) {
        return this.mapper.deleteByMenuId(menuIds);
    }

    @Override
    public int deleteByRoleAndRes(String roleId, List<String> menuIds) {
        return this.mapper.deleteByRoleAndRes(roleId, menuIds);
    }

    @Override
    public List<RoleMenu> query(RoleMenu roleRes) {
        return this.mapper.query(roleRes);
    }

    @Override
    public List<RoleMenu> queryWithCreatedBy(RoleMenu roleRes, Long createdBy) {
        return this.mapper.queryWithCreatedBy(roleRes, createdBy);
    }

    @Override
    public List<RoleMenu> selectByRoleId(Long roleId) {
        return this.mapper.selectByRoleId(roleId);
    }

    @Override
    public List<RoleMenu> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy) {
        return this.mapper.selectByRoleIdAndCreatedBy(roleId, createdBy);
    }
}
