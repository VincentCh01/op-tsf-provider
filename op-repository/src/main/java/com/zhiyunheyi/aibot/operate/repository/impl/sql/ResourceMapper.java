package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.vo.ResourceConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @menu:
 * @ClassName: ResourceMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:24
 * @Version: 1.0.0-SNAPSHOT
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    int insert(@Param("list") List<Resource> resources);

    int update(Resource resource);

    int delete(@Param("ids") List<Long> ids);

    List<Resource> select(@Param("ids") List<Long> ids);

    List<Resource> selectWithCreatedBy(@Param("ids") List<Long> ids, @Param("createdBy") Long createdBy);

    List<Resource> selectAll();

    List<Resource> selectAllWithCreatedBy(@Param("createdBy") Long createdBy);

    int count(@Param("condition") ResourceConditionVO condition);

    int countWithCreatedBy(@Param("condition") ResourceConditionVO condition, @Param("createdBy") Long createdBy);

    List<Resource> page(@Param("condition") ResourceConditionVO condition, @Param("offset") int offset, @Param("len") int len);

    List<Resource> pageWithCreatedBy(@Param("condition") ResourceConditionVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);
}
