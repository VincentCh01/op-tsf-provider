package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @menu:
 * @ClassName: RoleMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:24
 * @Version: 1.0.0-SNAPSHOT
 */
public interface MyRoleMapper extends BaseMapper<Role> {

    int insert(@Param("list") List<Role> roles);

    int update(Role role);

    int delete(@Param("ids") List<Long> ids);

    List<Role> select(@Param("ids") List<Long> ids);

    List<Role> selectWithCreatedBy(@Param("ids") List<Long> ids, @Param("createdBy") Long createdBy);

    int count(@Param("condition") RoleConditionVO condition);

    int countWithCreatedBy(@Param("condition") RoleConditionVO condition, @Param("createdBy") Long createdBy);

    List<Role> page(@Param("condition") RoleConditionVO condition, @Param("offset") int offset, @Param("len") int len);

    List<Role> pageWithCreatedBy(@Param("condition") RoleConditionVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);

    List<Role> selectAll();

    List<Role> selectAllWithCreatedBy(@Param("createdBy") Long createdBy);

    Role getByName(@Param("name") String name);

    Role getByNameAndCreatedBy(@Param("name") String name, @Param("createdBy") Long createdBy);
}
