package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.vo.ResourceConditionVO;

import java.util.List;

public interface IResourceService {
    int insert(List<Resource> resources);

    int update(Resource resource);

    int delete(List<Long> ids);

    PageResponse<Resource> page(ResourceConditionVO condition, int pageNo, int pageSize);

    PageResponse<Resource> pageWithCreatedBy(ResourceConditionVO condition, int pageNo, int pageSize, Long createdBy);

    List<Resource> selectAll();

    List<Resource> selectAllWithCreatedBy(Long createdBy);

    List<Resource> select(List<Long> resIds);

    List<Resource> selectWithCreatedBy(List<Long> resIds, Long createdBy);
}
