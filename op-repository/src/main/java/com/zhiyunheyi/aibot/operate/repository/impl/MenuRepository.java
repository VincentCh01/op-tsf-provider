package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.repository.IMenuRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.MenuMapper;
import com.zhiyunheyi.aibot.operate.vo.MenuConditionVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: MenuRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:36
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class MenuRepository implements IMenuRepository {

    @Resource(name = "myMenuMapper")
    private MenuMapper mapper;

    @Override
    public int insert(List<Menu> resources) {
        return this.mapper.insert(resources);
    }

    @Override
    public int update(Menu resource) {
        return this.mapper.update(resource);
    }

    @Override
    public int delete(List<Long> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public int count(MenuConditionVO condition) {
        return this.mapper.count(condition);
    }

    @Override
    public int countWithCreatedBy(MenuConditionVO condition, Long createdBy) {
        return this.mapper.countWithCreatedBy(condition, createdBy);
    }

    @Override
    public PageResponse<Menu> page(MenuConditionVO condition, int pageNo, int pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.count(condition);
        List<Menu> result = this.mapper.page(condition, offset, len);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public PageResponse<Menu> pageWithCreatedBy(MenuConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countWithCreatedBy(condition, createdBy);
        List<Menu> result = this.mapper.pageWithCreatedBy(condition, offset, len, createdBy);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public List<Menu> selectAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<Menu> selectAllWithCreatedBy(Long createdBy) {
        return this.mapper.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public List<Menu> select(List<Long> resIds) {
        return this.mapper.select(resIds);
    }

    @Override
    public List<Menu> selectWithCreatedBy(List<Long> resIds, Long createdBy) {
        return this.mapper.selectWithCreatedBy(resIds, createdBy);
    }
}
