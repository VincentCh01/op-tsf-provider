package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Menu;

import java.util.List;

public interface IMenuService {
    int insert(List<Menu> menus);

    int update(Menu menu);

    int delete(List<Long> ids);

    PageResponse<Menu> page(MenuCondition condition, int pageNo, int pageSize);

    PageResponse<Menu> pageWithCreatedBy(MenuCondition condition, int pageNo, int pageSize, Long createdBy);

    List<Menu> selectAll();

    List<Menu> selectAllWithCreatedBy(Long createdBy);

    List<Menu> select(List<Long> resIds);

    List<Menu> selectWithCreatedBy(List<Long> resIds, Long createdBy);
}
