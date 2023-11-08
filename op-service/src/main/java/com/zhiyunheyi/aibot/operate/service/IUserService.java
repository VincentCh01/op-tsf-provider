package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.User;

import java.util.List;

public interface IUserService {
    int insert(List<User> users);

    int update(User user);

    int delete(List<Long> ids);

    PageResponse<User> page(UserCondition condition, int pageNo, int pageSize);

    PageResponse<User> pageWithCreatedBy(UserCondition condition, int pageNo, int pageSize, Long createdBy);

    List<User> select(List<Long> userIds);

    List<User> selectWithCreatedBy(List<Long> userIds, Long createdBy);
}
