package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.repository.IRoleRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.MyRoleMapper;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:25
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class RoleRepository implements IRoleRepository {

    @Resource
    private MyRoleMapper mapper;

    @Override
    public int insert(List<Role> roles) {
        return this.mapper.insert(roles);
    }

    @Override
    public int update(Role role) {
        return this.mapper.update(role);
    }

    @Override
    public int delete(List<Long> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public int count(RoleConditionVO condition) {
        return this.mapper.count(condition);
    }

    @Override
    public int countWithCreatedBy(RoleConditionVO condition, Long createdBy) {
        return this.mapper.countWithCreatedBy(condition, createdBy);
    }

    @Override
    public PageResponse<Role> page(RoleConditionVO condition, int pageNo, int pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.count(condition);
        List<Role> result = this.mapper.page(condition, offset, len);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public PageResponse<Role> pageWithCreatedBy(RoleConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countWithCreatedBy(condition, createdBy);
        List<Role> result = this.mapper.pageWithCreatedBy(condition, offset, len, createdBy);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public List<Role> select(List<Long> ids) {
        return this.mapper.select(ids);
    }

    @Override
    public List<Role> selectWithCreatedBy(List<Long> ids, Long createdBy) {
        return this.mapper.selectWithCreatedBy(ids, createdBy);
    }

    @Override
    public List<Role> selectAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<Role> selectAllWithCreatedBy(Long createdBy) {
        return this.mapper.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public Role getByName(String name) {
        return this.mapper.getByName(name);
    }

    @Override
    public Role getByNameAndCreatedBy(String name, Long createdBy) {
        return this.mapper.getByNameAndCreatedBy(name, createdBy);
    }
}
