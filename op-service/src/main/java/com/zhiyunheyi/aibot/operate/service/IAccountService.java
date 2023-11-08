package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Account;

import java.util.List;

public interface IAccountService {
    int insert(List<Account> accounts);

    int update(Account account);

    int delete(List<Long> ids);

    List<Account> select(List<Long> ids);

    List<Account> selectWithCreatedBy(List<Long> ids, Long createdBy);

    List<Account> selectByRoleId(List<Long> roleIds);

    List<Account> selectByRoleIdAndCreatedBy(List<Long> roleIds, Long createdBy);

    PageResponse<Account> page(AccountCondition condition, int pageNo, int pageSize);

    PageResponse<Account> pageWithCreatedBy(AccountCondition condition, int pageNo, int pageSize, Long createdBy);

    PageResponse<Account> pageByKey(AccountQuery condition, Integer pageNo, Integer pageSize);

    PageResponse<Account> pageByKeyAndCreatedBy(AccountQuery condition, Integer pageNo, Integer pageSize, Long createdBy);

    Account getByUserId(Long userId);

    Account getByUserIdAndCreatedBy(Long userId, Long createdBy);

    Account getByMobile(String mobile);

    Account getByMobileAndCreatedBy(String mobile, Long createdBy);
}
