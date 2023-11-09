package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.operate.core.RoleMenu;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.repository.IRoleMenuRepository;
import com.zhiyunheyi.aibot.operate.service.IRoleMenuService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: RoleMenuService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:37
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class RoleMenuService implements IRoleMenuService {

    @Resource
    private IRoleMenuRepository repository;

    @Override
    @Transactional
    public int insert(List<RoleMenu> roleMenuList) {
        for (RoleMenu roleMenu : roleMenuList) {
            if (ObjectUtils.isNotEmpty(roleMenu.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            roleMenu.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(roleMenuList);
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
    public int deleteByMenuId(List<Long> resIds) {
        return this.repository.deleteByMenuId(resIds);
    }

    @Override
    @Transactional
    public int deleteByRoleAndRes(String roleId, List<String> resIds) {
        return this.repository.deleteByRoleAndRes(roleId, resIds);
    }

    @Override
    public List<RoleMenu> query(RoleMenu roleMenu) {
        return this.repository.query(roleMenu);
    }

    @Override
    public List<RoleMenu> queryWithCreatedBy(RoleMenu roleMenu, Long createdBy) {
        return this.repository.queryWithCreatedBy(roleMenu, createdBy);
    }

    @Override
    public List<RoleMenu> selectByRoleId(Long roleId) {
        return this.repository.selectByRoleId(roleId);
    }

    @Override
    public List<RoleMenu> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy) {
        return this.repository.selectByRoleIdAndCreatedBy(roleId, createdBy);
    }
}
