package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.operate.core.RoleRes;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.repository.IRoleResRepository;
import com.zhiyunheyi.aibot.operate.service.IRoleResService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleResService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:26
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class RoleResService implements IRoleResService {

    @Resource
    private IRoleResRepository repository;

    @Override
    @Transactional
    public int insert(List<RoleRes> roleResList) {
        for (RoleRes roleRes : roleResList) {
            if (ObjectUtils.isNotEmpty(roleRes.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            roleRes.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(roleResList);
    }

    @Override
    @Transactional
    public int delete(List<String> ids) {
        return this.repository.delete(ids);
    }

    @Override
    @Transactional
    public int deleteByRoleId(List<Long> roleIds) {
        return this.repository.deleteByRoleId(roleIds);
    }

    @Override
    @Transactional
    public int deleteByResId(List<Long> resIds) {
        return this.repository.deleteByResId(resIds);
    }

    @Override
    @Transactional
    public int deleteByRoleAndRes(String roleId, List<String> resIds) {
        return this.repository.deleteByRoleAndRes(roleId, resIds);
    }

    @Override
    public List<RoleRes> query(RoleRes roleRes) {
        return this.repository.query(roleRes);
    }

    @Override
    public List<RoleRes> queryWithCreatedBy(RoleRes roleRes, Long createdBy) {
        return this.repository.queryWithCreatedBy(roleRes, createdBy);
    }

    @Override
    public List<RoleRes> selectByRoleId(Long roleId) {
        return this.repository.selectByRoleId(roleId);
    }

    @Override
    public List<RoleRes> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy) {
        return this.repository.selectByRoleIdAndCreatedBy(roleId, createdBy);
    }
}
