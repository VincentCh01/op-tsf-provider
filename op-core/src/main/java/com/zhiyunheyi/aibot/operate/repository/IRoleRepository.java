package com.zhiyunheyi.aibot.operate.repository;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;

import java.util.List;

public interface IRoleRepository {
    int insert(List<Role> roles);

    int update(Role role);

    int delete(List<Long> ids);

    int count(RoleConditionVO condition);

    int countWithCreatedBy(RoleConditionVO condition, Long createdBy);

    PageResponse<Role> page(RoleConditionVO condition, int pageNo, int pageSize);

    PageResponse<Role> pageWithCreatedBy(RoleConditionVO condition, int pageNo, int pageSize, Long createdBy);

    List<Role> select(List<Long> ids);

    List<Role> selectWithCreatedBy(List<Long> ids, Long createdBy);

    List<Role> selectAll();

    List<Role> selectAllWithCreatedBy(Long createdBy);

    Role getByName(String name);

    Role getByNameAndCreatedBy(String name, Long createdBy);
}
