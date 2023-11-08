package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Role;

import java.util.List;

public interface IRoleService {
    int insert(List<Role> roles);

    int update(Role role);

    int delete(List<Long> ids);

    PageResponse<Role> page(RoleCondition condition, int pageNo, int pageSize);

    PageResponse<Role> pageWithCreatedBy(RoleCondition condition, int pageNo, int pageSize, Long createdBy);

    List<Role> select(List<Long> ids);

    List<Role> selectWithCreatedBy(List<Long> ids, Long createdBy);

    List<Role> selectAll();

    List<Role> selectAllWithCreatedBy(Long createdBy);

    Role getByName(String name);

    Role getByNameAndCreatedBy(String name, Long createdBy);
}
