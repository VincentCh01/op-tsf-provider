package com.zhiyunheyi.aibot.operate.repository;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.vo.MenuConditionVO;

import java.util.List;

public interface IMenuRepository {
    int insert(List<Menu> resources);

    int update(Menu resource);

    int delete(List<Long> ids);

    int count(MenuConditionVO condition);

    int countWithCreatedBy(MenuConditionVO condition, Long createdBy);

    PageResponse<Menu> page(MenuConditionVO condition, int pageNo, int pageSize);

    PageResponse<Menu> pageWithCreatedBy(MenuConditionVO condition, int pageNo, int pageSize, Long createdBy);

    List<Menu> selectAll();

    List<Menu> selectAllWithCreatedBy(Long createdBy);

    List<Menu> select(List<Long> resIds);

    List<Menu> selectWithCreatedBy(List<Long> resIds, Long createdBy);
}
