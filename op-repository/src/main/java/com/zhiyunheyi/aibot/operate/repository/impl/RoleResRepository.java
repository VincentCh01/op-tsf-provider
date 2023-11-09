package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.operate.core.RoleRes;
import com.zhiyunheyi.aibot.operate.repository.IRoleResRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.RoleResMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleResRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:25
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class RoleResRepository implements IRoleResRepository {

    @Resource
    private RoleResMapper mapper;

    @Override
    public int insert(List<RoleRes> roleResList) {
        return this.mapper.insert(roleResList);
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
    public int deleteByResId(List<Long> resIds) {
        return this.mapper.deleteByResId(resIds);
    }

    @Override
    public int deleteByRoleAndRes(String roleId, List<String> resIds) {
        return this.mapper.deleteByRoleAndRes(roleId, resIds);
    }

    @Override
    public List<RoleRes> query(RoleRes roleRes) {
        return this.mapper.query(roleRes);
    }

    @Override
    public List<RoleRes> queryWithCreatedBy(RoleRes roleRes, Long createdBy) {
        return this.mapper.queryWithCreatedBy(roleRes, createdBy);
    }

    @Override
    public List<RoleRes> selectByRoleId(Long roleId) {
        return this.mapper.selectByRoleId(roleId);
    }

    @Override
    public List<RoleRes> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy) {
        return this.mapper.selectByRoleIdAndCreatedBy(roleId, createdBy);
    }
}
