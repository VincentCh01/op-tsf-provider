package com.zhiyunheyi.aibot.operate.service.impl;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.core.dto.request.MenuCondition;
import com.zhiyunheyi.aibot.operate.service.IMenuService;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.userservice.operate.repository.MenuRepository;
import com.zhiyunheyi.aibot.domain.core.utils.SequenceUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @menu:
 * @ClassName: MenuService
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:37
 * @Version: 1.0.0-SNAPSHOT
 */
@Service("myMenuService")
public class MenuService implements IMenuService {

    @Resource
    private MenuRepository repository;

    @Override
    @Transactional
    public int insert(List<Menu> menus) {
        for (Menu menu : menus) {
            if (ObjectUtils.isNotEmpty(menu.getId())) {
                throw new AccountException(ResultEnum.PARAM_ERROR, "id必须为空");
            }

            menu.setId(SequenceUtil.nextId());
        }

        return this.repository.insert(menus);
    }

    @Override
    @Transactional
    public int update(Menu menu) {
        if (ObjectUtils.isEmpty(menu.getId())) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "id不能为空");
        }

        return this.repository.update(menu);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        return this.repository.delete(ids);
    }

    @Override
    public PageResponse<Menu> page(MenuCondition condition, int pageNo, int pageSize) {
        return this.repository.page(condition, pageNo, pageSize);
    }

    @Override
    public PageResponse<Menu> pageWithCreatedBy(MenuCondition condition, int pageNo, int pageSize, Long createdBy) {
        return this.repository.pageWithCreatedBy(condition, pageNo, pageSize, createdBy);
    }

    @Override
    public List<Menu> selectAll() {
        return this.repository.selectAll();
    }

    @Override
    public List<Menu> selectAllWithCreatedBy(Long createdBy) {
        return this.repository.selectAllWithCreatedBy(createdBy);
    }

    @Override
    public List<Menu> select(List<Long> resIds) {
        return this.repository.select(resIds);
    }

    @Override
    public List<Menu> selectWithCreatedBy(List<Long> resIds, Long createdBy) {
        return this.repository.selectWithCreatedBy(resIds, createdBy);
    }
}
