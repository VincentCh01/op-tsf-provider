package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @menu:
 * @ClassName: AccountMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:24
 * @Version: 1.0.0-SNAPSHOT
 */
public interface AccountMapper extends BaseMapper<Account> {
    int insert(@Param("list") List<Account> accounts);

    int update(Account account);

    int updatePassword(String id, String password);

    Account updateRole(String id, int role);

    int delete(@Param("ids") List<Long> ids);

    List<Account> select(@Param("ids") List<Long> ids);

    List<Account> selectWithCreatedBy(@Param("ids") List<Long> ids, @Param("createdBy") Long createdBy);

    List<Account> selectByRoleId(@Param("roleIds") List<Long> roleIds);

    List<Account> selectByRoleIdAndCreatedBy(@Param("roleIds") List<Long> roleIds, @Param("createdBy") Long createdBy);

    Account getByUserId(@Param("userId") Long userId);

    Account getByUserIdAndCreatedBy(@Param("userId") Long userId, @Param("createdBy") Long createdBy);

    Account getByMobile(@Param("mobile") String mobile);

    Account getByMobileAndCreatedBy(@Param("mobile") String mobile, @Param("createdBy") Long createdBy);

    int count(@Param("condition") AccountConditionVO condition);

    int countWithCreatedBy(@Param("condition") AccountConditionVO condition, @Param("createdBy") Long createdBy);

    List<Account> page(@Param("condition") AccountConditionVO condition, @Param("offset") int offset, @Param("len") int len);

    List<Account> pageWithCreatedBy(@Param("condition") AccountConditionVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);

    int countByKey(@Param("condition") AccountQueryVO condition);

    int countByKeyAndCreatedBy(@Param("condition") AccountQueryVO condition, @Param("createdBy") Long createdBy);

    List<Account> pageByKey(@Param("condition") AccountQueryVO condition, @Param("offset") int offset, @Param("len") int len);

    List<Account> pageByKeyAndCreatedBy(@Param("condition") AccountQueryVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);
}
