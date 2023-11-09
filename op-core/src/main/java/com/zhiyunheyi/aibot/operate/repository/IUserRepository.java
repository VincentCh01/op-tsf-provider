package com.zhiyunheyi.aibot.operate.repository;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.vo.UserConditionVO;

import java.util.List;

public interface IUserRepository {
    int insert(List<User> users);

    int update(User user);

    int delete(List<Long> ids);

    int count(UserConditionVO condition);

    int countWithCreatedBy(UserConditionVO condition, Long createdBy);

    PageResponse<User> page(UserConditionVO condition, int pageNo, int pageSize);

    PageResponse<User> pageWithCreatedBy(UserConditionVO condition, int pageNo, int pageSize, Long createdBy);

    List<User> select(List<Long> userIds);

    List<User> selectWithCreatedBy(List<Long> userIds, Long createdBy);
}
