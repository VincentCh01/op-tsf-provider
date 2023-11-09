package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.vo.MenuConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @menu:
 * @ClassName: MenuMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 11:41
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository("myMenuMapper")
public interface MenuMapper extends BaseMapper<Menu> {
    int insert(@Param("list") List<Menu> menus);

    int update(Menu menu);

    int delete(@Param("ids") List<Long> ids);

    List<Menu> select(@Param("ids") List<Long> ids);

    List<Menu> selectWithCreatedBy(@Param("ids") List<Long> ids, @Param("createdBy") Long createdBy);

    List<Menu> selectAll();

    List<Menu> selectAllWithCreatedBy(@Param("createdBy") Long createdBy);

    int count(@Param("condition") MenuConditionVO condition);

    int countWithCreatedBy(@Param("condition") MenuConditionVO condition, @Param("createdBy") Long createdBy);

    List<Menu> page(@Param("condition") MenuConditionVO condition, @Param("offset") int offset, @Param("len") int len);

    List<Menu> pageWithCreatedBy(@Param("condition") MenuConditionVO condition, @Param("offset") int offset, @Param("len") int len, @Param("createdBy") Long createdBy);
}
