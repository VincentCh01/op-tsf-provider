package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.repository.IUserRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.UserMapper;
import com.zhiyunheyi.aibot.operate.vo.UserConditionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @menu:
 * @ClassName: UserRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:23
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class UserRepository implements IUserRepository {

    @javax.annotation.Resource
    private UserMapper mapper;

    @Override
    public int insert(List<User> users) {
        return this.mapper.insert(users);
    }

    @Override
    public int update(User user) {
        return this.mapper.update(user);
    }

    @Override
    public int delete(List<Long> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public int count(UserConditionVO condition) {
        return this.mapper.count(condition);
    }

    @Override
    public int countWithCreatedBy(UserConditionVO condition, Long createdBy) {
        return this.mapper.countWithCreatedBy(condition, createdBy);
    }

    @Override
    public PageResponse<User> page(UserConditionVO condition, int pageNo, int pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.count(condition);
        List<User> result = this.mapper.page(condition, offset, len);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public PageResponse<User> pageWithCreatedBy(UserConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countWithCreatedBy(condition, createdBy);
        List<User> result = this.mapper.pageWithCreatedBy(condition, offset, len, createdBy);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public List<User> select(List<Long> userIds) {
        return this.mapper.select(userIds);
    }

    @Override
    public List<User> selectWithCreatedBy(List<Long> userIds, Long createdBy) {
        return this.mapper.selectWithCreatedBy(userIds, createdBy);
    }
}
