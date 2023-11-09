package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.vo.UserConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @menu:
 * @ClassName: UserMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:13
 * @Version: 1.0.0-SNAPSHOT
 */
public interface UserMapper extends BaseMapper<User> {

    int insert(@Param("list") List<User> users);

    int update(User user);

    int delete(@Param("ids") List<Long> ids);

    List<User> select(@Param("ids") List<Long> ids);

    List<User> selectWithCreatedBy(@Param("ids") List<Long> ids, @Param("createdBy") Long createdBy);

    List<User> selectByMobile(@Param("mobiles") List<String> mobiles);

    List<User> selectByMobileAndCreatedBy(@Param("mobiles") List<String> mobiles, @Param("createdBy") Long createdBy);

    int count(@Param("condition") UserConditionVO condition);

    int countWithCreatedBy(@Param("condition") UserConditionVO condition, @Param("createdBy") Long createdBy);

    List<User> page(@Param("condition") UserConditionVO condition, @Param("offset") int offset, @Param("len") int len);

    List<User> pageWithCreatedBy(@Param("condition") UserConditionVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);
}
