package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.repository.IRoleRepository;
import com.zhiyunheyi.aibot.operate.service.IRoleService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:22
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class RoleService implements IRoleService {

    @Resource
    private IRoleRepository repository;

    @Override
    @Transactional
    public int insert(List<Role> roles) {
        for (Role role : roles) {
            if (ObjectUtils.isNotEmpty(role.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            role.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(roles);
    }

    @Override
    @Transactional
    public int update(Role role) {
        if (ObjectUtils.isEmpty(role.getId())) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "id不能为空");
        }

        return this.repository.update(role);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.repository.delete(ids);
    }

    @Override
    public PageResponse<Role> page(RoleConditionVO condition, int pageNo, int pageSize) {
        return this.repository.page(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<Role> pageWithCreatedBy(RoleConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        return this.repository.pageWithCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public List<Role> select(List<Long> ids) {
        return this.repository.select(ids);
    }

    @Override
    public List<Role> selectWithCreatedBy(List<Long> ids, Long createdBy) {
        return this.repository.selectWithCreatedBy(ids, createdBy);
    }

    @Override
    public List<Role> selectAll() {
        return this.repository.selectAll();
    }

    @Override
    public List<Role> selectAllWithCreatedBy(Long createdBy) {
        return this.repository.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public Role getByName(String name) {
        return this.repository.getByName(name);
    }

    @Override
    public Role getByNameAndCreatedBy(String name, Long createdBy) {
        return this.repository.getByNameAndCreatedBy(name, createdBy);
    }
}
