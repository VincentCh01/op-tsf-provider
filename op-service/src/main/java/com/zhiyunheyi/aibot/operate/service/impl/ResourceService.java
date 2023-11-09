package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.repository.IResourceRepository;
import com.zhiyunheyi.aibot.operate.service.IResourceService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import com.zhiyunheyi.aibot.operate.vo.ResourceConditionVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @menu:
 * @ClassName: ResourceService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:25
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class ResourceService implements IResourceService {

    @javax.annotation.Resource
    private IResourceRepository repository;

    @Override
    @Transactional
    public int insert(List<Resource> resources) {
        for (Resource resource : resources) {
            if (ObjectUtils.isNotEmpty(resource.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            resource.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(resources);
    }

    @Override
    @Transactional
    public int update(Resource resource) {
        if (ObjectUtils.isEmpty(resource.getId())) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "id不能为空");
        }

        return this.repository.update(resource);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.repository.delete(ids);
    }

    @Override
    public PageResponse<Resource> page(ResourceConditionVO condition, int pageNo, int pageSize) {
        return this.repository.page(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<Resource> pageWithCreatedBy(ResourceConditionVO condition, int pageNo, int pageSize, Long createdBy) {
        return this.repository.pageWithCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public List<Resource> selectAll() {
        return this.repository.selectAll();
    }

    @Override
    public List<Resource> selectAllWithCreatedBy(Long createdBy) {
        return this.repository.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public List<Resource> select(List<Long> resIds) {
        return this.repository.select(resIds);
    }

    @Override
    public List<Resource> selectWithCreatedBy(List<Long> resIds, Long createdBy) {
        return this.repository.selectWithCreatedBy(resIds, createdBy);
    }
}
