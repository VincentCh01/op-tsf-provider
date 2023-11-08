package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.core.dto.request.UserCondition;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.service.IUserService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.userservice.operate.repository.UserRepository;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: UserService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:22
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserRepository repository;

    @Override
    @Transactional
    public int insert(List<User> users) {
        for (User user : users) {
            if (ObjectUtils.isNotEmpty(user.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            user.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(users);
    }

    @Override
    @Transactional
    public int update(User user) {
        if (ObjectUtils.isEmpty(user.getId())) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "id不能为空");
        }

        return this.repository.update(user);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.repository.delete(ids);
    }

    @Override
    public PageResponse<User> page(UserCondition condition, int pageNo, int pageSize) {
        return this.repository.page(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<User> pageWithCreatedBy(UserCondition condition, int pageNo, int pageSize, Long createdBy) {
        return this.repository.pageWithCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public List<User> select(List<Long> userIds) {
        return this.repository.select(userIds);
    }

    @Override
    public List<User> selectWithCreatedBy(List<Long> userIds, Long createdBy) {
        return this.repository.selectWithCreatedBy(userIds, createdBy);
    }
}
