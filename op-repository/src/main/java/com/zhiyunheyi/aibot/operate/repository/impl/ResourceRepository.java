package com.zhiyunheyi.aibot.operate.repository.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.repository.IResourceRepository;
import com.zhiyunheyi.aibot.operate.repository.impl.sql.ResourceMapper;
import com.zhiyunheyi.aibot.operate.vo.ResourceConditionVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @menu:
 * @ClassName: ResourceRepository
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:25
 * @Version: 1.0.0-SNAPSHOT
 */
@Repository
public class ResourceRepository implements IResourceRepository {

    @javax.annotation.Resource
    private ResourceMapper mapper;

    @Override
    public int insert(List<Resource> resources) {
        return this.mapper.insert(resources);
    }

    @Override
    public int update(Resource resource) {
        return this.mapper.update(resource);
    }

    @Override
    public int delete(List<Long> ids) {
        return this.mapper.delete(ids);
    }

    @Override
    public int count(ResourceConditionVO condition) {
        return this.mapper.count(condition);
    }

    @Override
    public int countWithCreatedBy(ResourceConditionVO condition, Long createdBy) {
        return this.mapper.countWithCreatedBy(condition, createdBy);
    }

    @Override
    public PageResponse<Resource> page(ResourceConditionVO condition, int pageNo, int pageSize) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.count(condition);
        List<Resource> result = this.mapper.page(condition, offset, len);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public PageResponse<Resource> pageWithCreatedBy(ResourceConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        int len = pageSize < 1 ? 10 : pageSize;
        int offset = pageNo < 1 ? 0 : (pageNo - 1) * len;

        int totalSize = this.mapper.countWithCreatedBy(condition, createdBy);
        List<Resource> result = this.mapper.pageWithCreatedBy(condition, offset, len, createdBy);

        return new PageResponse<>(pageNo, pageSize, totalSize, result);
    }

    @Override
    public List<Resource> selectAll() {
        return this.mapper.selectAll();
    }

    @Override
    public List<Resource> selectAllWithCreatedBy(Long createdBy) {
        return this.mapper.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public List<Resource> select(List<Long> resIds) {
        return this.mapper.select(resIds);
    }

    @Override
    public List<Resource> selectWithCreatedBy(List<Long> resIds, Long createdBy) {
        return this.mapper.selectWithCreatedBy(resIds, createdBy);
    }
}
