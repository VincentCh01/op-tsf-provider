package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.operate.repository.IAccountRepository;
import com.zhiyunheyi.aibot.operate.service.IAccountService;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: AccountService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:22
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class AccountService implements IAccountService {

    @Resource
    private IAccountRepository repository;

    @Override
    @Transactional
    public int insert(List<Account> accounts) {
        for (Account account : accounts) {
            if (ObjectUtils.isNotEmpty(account.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            account.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(accounts);
    }

    @Override
    @Transactional
    public int update(Account account) {
        if (ObjectUtils.isEmpty(account.getId())) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "id不能为空");
        }

        return this.repository.update(account);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.repository.delete(ids);
    }

    @Override
    public List<Account> select(List<Long> ids) {
        return this.repository.select(ids);
    }

    @Override
    public List<Account> selectWithCreatedBy(List<Long> ids, Long createdBy) {
        return this.repository.selectWithCreatedBy(ids, createdBy);
    }

    @Override
    public List<Account> selectByRoleId(List<Long> roleIds) {
        return this.repository.selectByRoleId(roleIds);
    }

    @Override
    public List<Account> selectByRoleIdAndCreatedBy(List<Long> roleIds, Long createdBy) {
        return this.repository.selectByRoleIdAndCreatedBy(roleIds, createdBy);
    }

    @Override
    public PageResponse<Account> page(AccountConditionVO condition, int pageNo, int pageSize) {
        return this.repository.page(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<Account> pageWithCreatedBy(AccountConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        return this.repository.pageWithCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public PageResponse<Account> pageByKey(AccountQueryVO condition, Integer pageNo, Integer pageSize) {
        return this.repository.pageByKey(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<Account> pageByKeyAndCreatedBy(AccountQueryVO condition, Integer pageNo, Integer pageSize, Long createdBy) {
        return this.repository.pageByKeyAndCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public Account getByUserId(Long userId) {
        return this.repository.getByUserId(userId);
    }

    @Override
    public Account getByUserIdAndCreatedBy(Long userId, Long createdBy) {
        return this.repository.getByUserIdAndCreatedBy(userId, createdBy);
    }

    @Override
    public Account getByMobile(String mobile) {
        return this.repository.getByMobile(mobile);
    }

    @Override
    public Account getByMobileAndCreatedBy(String mobile, Long createdBy) {
        return this.repository.getByMobileAndCreatedBy(mobile, createdBy);
    }
}
