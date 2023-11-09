package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.repository.IAccountRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.AccountMapper;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: AccountRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:25
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class AccountRepository implements IAccountRepository {

    @Resource
    private AccountMapper mapper;

    @Override
    public int insert(List<Account> accounts) {
        return this.mapper.insert(accounts);
    }

    @Override
    public int update(Account account) {
        return this.mapper.update(account);
    }

    @Override
    public int updatePassword(String id, String password) {
        return this.mapper.updatePassword(id, password);
    }

    @Override
    public int updateRole(String id, int role) {
        return this.update(this.mapper.updateRole(id, role));
    }

    @Override
    public int delete(List<Long> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public List<Account> select(List<Long> ids) {
        return this.mapper.select(ids);
    }

    @Override
    public List<Account> selectWithCreatedBy(List<Long> ids, Long createdBy) {
        return this.mapper.selectWithCreatedBy(ids, createdBy);
    }

    @Override
    public List<Account> selectByRoleId(List<Long> roleIds) {
        return this.mapper.selectByRoleId(roleIds);
    }

    @Override
    public List<Account> selectByRoleIdAndCreatedBy(List<Long> roleIds, Long createdBy) {
        return this.mapper.selectByRoleIdAndCreatedBy(roleIds, createdBy);
    }

    @Override
    public int count(AccountConditionVO condition) {
        return this.mapper.count(condition);
    }

    @Override
    public int countWithCreatedBy(AccountConditionVO condition, Long createdBy) {
        return this.mapper.countWithCreatedBy(condition, createdBy);
    }

    @Override
    public PageResponse<Account> page(AccountConditionVO condition, int pageNo, int pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.count(condition);
        if (totalSize > 0) {
            List<Account> result = this.mapper.page(condition, offset, len);
            return new PageResponse<>(pageNo, pageSize, totalSize, result);
        }

        return new PageResponse<>(pageNo, pageSize, totalSize, Lists.newArrayList());
    }

    @Override
    public PageResponse<Account> pageWithCreatedBy(AccountConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countWithCreatedBy(condition, createdBy);
        if (totalSize > 0) {
            List<Account> result = this.mapper.pageWithCreatedBy(condition, offset, len, createdBy);
            return new PageResponse<>(pageNo, pageSize, totalSize, result);
        }

        return new PageResponse<>(pageNo, pageSize, totalSize, Lists.newArrayList());
    }

    @Override
    public PageResponse<Account> pageByKey(AccountQueryVO condition, Integer pageNo, Integer pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countByKey(condition);
        if (totalSize > 0) {
            List<Account> result = this.mapper.pageByKey(condition, offset, len);
            return new PageResponse<>(pageNo, pageSize, totalSize, result);
        }

        return new PageResponse<>(pageNo, pageSize, totalSize, Lists.newArrayList());
    }

    @Override
    public PageResponse<Account> pageByKeyAndCreatedBy(AccountQueryVO condition, Integer pageNo, Integer pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countByKeyAndCreatedBy(condition, createdBy);
        if (totalSize > 0) {
            List<Account> result = this.mapper.pageByKeyAndCreatedBy(condition, offset, len, createdBy);
            return new PageResponse<>(pageNo, pageSize, totalSize, result);
        }

        return new PageResponse<>(pageNo, pageSize, totalSize, Lists.newArrayList());
    }

    @Override
    public Account getByUserId(Long userId) {
        return this.mapper.getByUserId(userId);
    }

    @Override
    public Account getByUserIdAndCreatedBy(Long userId, Long createdBy) {
        return this.mapper.getByUserIdAndCreatedBy(userId, createdBy);
    }

    @Override
    public Account getByMobile(String mobile) {
        return this.mapper.getByMobile(mobile);
    }

    @Override
    public Account getByMobileAndCreatedBy(String mobile, Long createdBy) {
        return this.mapper.getByMobileAndCreatedBy(mobile, createdBy);
    }
}
