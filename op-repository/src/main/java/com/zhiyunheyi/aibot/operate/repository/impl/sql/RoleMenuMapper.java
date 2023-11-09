package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @menu:
 * @ClassName: RoleMenuMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 11:41
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository("myRoleMenuMapper")
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    int insert(@Param("list") List<RoleMenu> roleMenuList);

    int delete(@Param("ids") List<String> ids);

    int deleteByRoleId(@Param("roleIds") List<Long> roleId);

    int deleteByMenuId(@Param("menuIds") List<Long> menuIds);

    int deleteByRoleAndRes(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);

    List<RoleMenu> query(@Param("roleRes") RoleMenu roleRes);

    List<RoleMenu> queryWithCreatedBy(@Param("roleRes") RoleMenu roleRes, @Param("createdBy") Long createdBy);

    List<RoleMenu> selectByRoleId(@Param("roleId") Long roleId);

    List<RoleMenu> selectByRoleIdAndCreatedBy(@Param("roleId") Long roleId, @Param("createdBy") Long createdBy);
}
