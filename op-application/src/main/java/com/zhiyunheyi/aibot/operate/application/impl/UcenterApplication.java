package com.zhiyunheyi.aibot.operate.application.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import com.zhiyunheyi.aibot.domain.core.utils.AssembleUtil;
import com.zhiyunheyi.aibot.operate.application.IUcenterApplication;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.core.RoleMenu;
import com.zhiyunheyi.aibot.operate.core.RoleRes;
import com.zhiyunheyi.aibot.operate.core.User;
import com.zhiyunheyi.aibot.operate.exception.AccountException;
import com.zhiyunheyi.aibot.operate.service.IAccountService;
import com.zhiyunheyi.aibot.operate.service.IMenuService;
import com.zhiyunheyi.aibot.operate.service.IResourceService;
import com.zhiyunheyi.aibot.operate.service.IRoleMenuService;
import com.zhiyunheyi.aibot.operate.service.IRoleResService;
import com.zhiyunheyi.aibot.operate.service.IRoleService;
import com.zhiyunheyi.aibot.operate.service.IUserService;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import com.zhiyunheyi.aibot.operate.vo.AuthTokenVO;
import com.zhiyunheyi.aibot.operate.vo.MenuTreeVO;
import com.zhiyunheyi.aibot.operate.vo.ResourceTreeVO;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import com.zhiyunheyi.aibot.operate.vo.RoleCreateVO;
import com.zhiyunheyi.aibot.operate.vo.UserContext;
import com.zhiyunheyi.aibot.operate.vo.UserInfoVO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @menu:
 * @ClassName: UcenterApp
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 9:49
 * @Version: 1.0.0-SNAPSHOT
 */
@Service
public class UcenterApplication implements IUcenterApplication {
    @javax.annotation.Resource
    private IAccountService accountService;

    @javax.annotation.Resource
    private IUserService userService;

    @javax.annotation.Resource
    private IRoleService roleService;

    @javax.annotation.Resource
    private IRoleResService roleResService;

    @javax.annotation.Resource
    private IResourceService resourceService;

    @javax.annotation.Resource
    private IMenuService menuService;

    @javax.annotation.Resource
    private IRoleMenuService roleMenuService;

    @Override
    @Transactional
    public int insert(Account account) {
        Account select = this.accountService.getByMobile(account.getMobile());
        if (select != null) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "手机号重复");
        }

        AuthTokenVO tokenVO = UserContext.getUser();
        User user = new User();
        user.setEmail(account.getEmail());
        user.setMobile(account.getMobile());
        user.setNickName(account.getNickname());
        user.setCreatedBy(tokenVO.getUserId());
        user.setUpdatedBy(tokenVO.getUserId());

        this.userService.insert(Lists.newArrayList(user));

        account.setUserId(user.getId());
        account.setCreatedBy(tokenVO.getUserId());
        account.setUpdatedBy(tokenVO.getUserId());
        return this.accountService.insert(Lists.newArrayList(account));
    }

    @Override
    @Transactional
    public int update(Account request) {
        List<Account> accounts = this.accountService.select(Lists.newArrayList(request.getId()));
        if (CollectionUtils.isEmpty(accounts)) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "参数错误");
        }

        AuthTokenVO tokenVO = UserContext.getUser();
        Account account = accounts.get(0);
        User user = new User();
        user.setId(account.getUserId());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setNickName(request.getNickname());
        user.setUpdatedBy(tokenVO.getUserId());
        this.userService.update(user);

        request.setUpdatedBy(tokenVO.getUserId());
        return this.accountService.update(request);
    }

    @Override
    @Transactional
    public int delete(List<Long> ids) {
        List<Account> accounts = this.accountService.select(ids);
        if (CollectionUtils.isEmpty(accounts)) {
            return 0;
        }

        List<Long> userIds = accounts.stream().map(Account::getUserId).collect(Collectors.toList());
        this.userService.delete(userIds);
        return this.accountService.delete(ids);
    }

    @Override
    @SneakyThrows
    public UserInfoVO getByUserId(Long userId) {
        Account account = this.accountService.getByUserId(userId);
        if (account == null) {
            return null;
        }

        UserInfoVO userInfo = AssembleUtil.to(account, UserInfoVO.class);
        List<Role> roles = this.roleService.select(Lists.newArrayList(userInfo.getRoleId()));
        if (CollectionUtils.isEmpty(roles)) {
            return userInfo;
        }

        Role role = roles.get(0);
        userInfo.setRoleName(role.getName());

        return userInfo;
    }

    @Override
    @SneakyThrows
    public UserInfoVO getByMobile(String mobile) {
        Account account = this.accountService.getByMobile(mobile);
        if (account == null) {
            return null;
        }

        UserInfoVO userInfo = AssembleUtil.to(account, UserInfoVO.class);
        List<Role> roles = this.roleService.select(Lists.newArrayList(userInfo.getRoleId()));
        if (CollectionUtils.isEmpty(roles)) {
            return userInfo;
        }

        Role role = roles.get(0);
        userInfo.setRoleName(role.getName());
        userInfo.setType(role.getType());

        return userInfo;
    }

    @Override
    public PageResponse<UserInfoVO> page(AccountConditionVO condition, Integer pageNo, Integer pageSize) {
        PageResponse<Account> page = this.accountService.page(condition, pageNo, pageSize);
        return this.getUserInfoPageResponse(page);
    }

    @Override
    @SneakyThrows
    public PageResponse<UserInfoVO> pageByKey(AccountQueryVO condition, Integer pageNo, Integer pageSize) {
        PageResponse<Account> page = this.accountService.pageByKey(condition, pageNo, pageSize);
        return this.getUserInfoPageResponse(page);
    }

    @Override
    @Transactional
    public int insertRole(RoleCreateVO req) {
        Role select = this.roleService.getByName(req.getName());
        if (select != null) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "角色名重复");
        }

        AuthTokenVO tokenVO = UserContext.getUser();
        Role role = new Role();
        role.setName(req.getName());
        role.setType(req.getType());
        role.setDesc(req.getDesc());
        role.setCreatedBy(tokenVO.getUserId());
        role.setUpdatedBy(tokenVO.getUserId());

        int result = this.roleService.insert(Lists.newArrayList(role));
        if (!CollectionUtils.isEmpty(req.getMenuIds())) {
            List<RoleMenu> roleMenuList = Lists.newArrayListWithCapacity(req.getMenuIds().size());
            for (Long menuId : req.getMenuIds()) {
                RoleMenu roleRes = new RoleMenu();
                roleRes.setMenuId(menuId);
                roleRes.setRoleId(role.getId());
                roleRes.setCreatedBy(tokenVO.getUserId());
                roleRes.setUpdatedBy(tokenVO.getUserId());
                roleMenuList.add(roleRes);
            }

            this.roleMenuService.insert(roleMenuList);
        }

        if (!CollectionUtils.isEmpty(req.getResourceIds())) {
            this.roleResService.deleteByRoleId(Lists.newArrayList(role.getId()));
            List<RoleRes> roleResList = Lists.newArrayListWithCapacity(req.getResourceIds().size());
            for (Long resourceId : req.getResourceIds()) {
                RoleRes roleRes = new RoleRes();
                roleRes.setResId(resourceId);
                roleRes.setRoleId(role.getId());
                roleRes.setCreatedBy(tokenVO.getUserId());
                roleRes.setUpdatedBy(tokenVO.getUserId());
                roleResList.add(roleRes);
            }

            this.roleResService.insert(roleResList);
        }

        return result;
    }

    @Override
    @Transactional
    public int updateRole(RoleCreateVO req) {
        AuthTokenVO tokenVO = UserContext.getUser();
        
        Role role = new Role();
        role.setId(req.getId());
        role.setName(req.getName());
        role.setType(req.getType());
        role.setDesc(req.getDesc());
        role.setUpdatedBy(tokenVO.getUserId());

        if (!CollectionUtils.isEmpty(req.getMenuIds())) {
            this.roleMenuService.deleteByRoleId(Lists.newArrayList(role.getId()));
            List<RoleMenu> roleMenuList = Lists.newArrayListWithCapacity(req.getMenuIds().size());
            for (Long menuId : req.getMenuIds()) {
                RoleMenu roleRes = new RoleMenu();
                roleRes.setMenuId(menuId);
                roleRes.setRoleId(role.getId());
                roleRes.setCreatedBy(tokenVO.getUserId());
                roleRes.setUpdatedBy(tokenVO.getUserId());
                roleMenuList.add(roleRes);
            }

            this.roleMenuService.insert(roleMenuList);
        }

        if (!CollectionUtils.isEmpty(req.getResourceIds())) {
            this.roleResService.deleteByRoleId(Lists.newArrayList(role.getId()));
            List<RoleRes> roleResList = Lists.newArrayListWithCapacity(req.getResourceIds().size());
            for (Long resourceId : req.getResourceIds()) {
                RoleRes roleRes = new RoleRes();
                roleRes.setResId(resourceId);
                roleRes.setRoleId(role.getId());
                roleRes.setCreatedBy(tokenVO.getUserId());
                roleRes.setUpdatedBy(tokenVO.getUserId());
                roleResList.add(roleRes);
            }

            this.roleResService.insert(roleResList);
        }

        return this.roleService.update(role);
    }

    @Override
    @Transactional
    public int deleteRole(List<Long> ids) {
        List<Account> select = this.accountService.selectByRoleId(ids);
        if (!CollectionUtils.isEmpty(select)) {
            throw new AccountException(ResultEnum.PARAM_ERROR, "角色正在使用中");
        }

        return this.roleService.delete(ids);
    }

    @Override
    public PageResponse<Role> pageRole(RoleConditionVO condition, Integer pageNo, Integer pageSize) {
        PageResponse<Role> page = this.roleService.page(condition, pageNo, pageSize);
        if (CollectionUtils.isEmpty(page.getList())) {
            return new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), Lists.newArrayList());
        }

        return page;
    }

    @Override
    public List<ResourceTreeVO> treeAll() {
        AuthTokenVO user = UserContext.getUser();
        List<Resource> resources = isAdmin(user) ? this.resourceService.selectAll() : this.resourceService.selectAllWithCreatedBy(user.getUserId());

        return this.getResourceTrees(resources);
    }

    @Override
    public List<ResourceTreeVO> treeByRoleId(Long roleId) {
        AuthTokenVO user = UserContext.getUser();
        List<RoleRes> roleResList = isAdmin(user) ? this.roleResService.selectByRoleId(roleId) : this.roleResService.selectByRoleIdAndCreatedBy(roleId, user.getUserId());
        List<Long> resIds = roleResList.stream().map(RoleRes::getResId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resIds)) {
            return Lists.newArrayList();
        }

        return this.getResourceTrees(this.resourceService.select(resIds));
    }

    @Override
    public List<Resource> selectByRoleId(Long roleId) {
        List<RoleRes> roleResList = this.roleResService.selectByRoleId(roleId);
        List<Long> resIds = roleResList.stream().map(RoleRes::getResId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resIds)) {
            return Lists.newArrayList();
        }

        return this.resourceService.select(resIds);
    }

    @Override
    public List<Resource> selectByRoleIdAndUserId(Long roleId) {
        AuthTokenVO user = UserContext.getUser();
        List<RoleRes> roleResList = this.roleResService.selectByRoleIdAndCreatedBy(roleId, user.getUserId());
        List<Long> resIds = roleResList.stream().map(RoleRes::getResId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resIds)) {
            return Lists.newArrayList();
        }

        return this.resourceService.select(resIds);
    }

    @Override
    public List<Resource> selectAllResource() {
        AuthTokenVO user = UserContext.getUser();
        return isAdmin(user) ? this.resourceService.selectAll() : this.resourceService.selectAllWithCreatedBy(user.getUserId());
    }

    @Override
    public List<Role> selectAllRole() {
        AuthTokenVO user = UserContext.getUser();
        return isAdmin(user) ? this.roleService.selectAll() : this.roleService.selectAllWithCreatedBy(user.getUserId());
    }

    @Override
    public List<MenuTreeVO> treeAllMenu() {
        AuthTokenVO user = UserContext.getUser();
        List<Menu> menus = isAdmin(user) ? this.menuService.selectAll() : this.menuService.selectAllWithCreatedBy(user.getUserId());

        return this.getMenuTrees(menus);
    }

    @Override
    public List<MenuTreeVO> treeMenuByRoleId(Long roleId) {
        AuthTokenVO user = UserContext.getUser();
        List<RoleMenu> roleMenuList = isAdmin(user) ? this.roleMenuService.selectByRoleId(roleId) : this.roleMenuService.selectByRoleIdAndCreatedBy(roleId, user.getUserId());
        List<Long> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }

        return this.getMenuTrees(this.menuService.select(menuIds));
    }

    @Override
    public List<Menu> selectMenuByRoleId(Long roleId) {
        AuthTokenVO user = UserContext.getUser();
        List<RoleMenu> roleMenuList = isAdmin(user) ? this.roleMenuService.selectByRoleId(roleId) : this.roleMenuService.selectByRoleIdAndCreatedBy(roleId, user.getUserId());
        List<Long> menuIds = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }

        return this.menuService.select(menuIds);
    }

    @Override
    public List<Menu> selectAllMenu() {
        AuthTokenVO user = UserContext.getUser();
        return isAdmin(user) ? this.menuService.selectAll() : this.menuService.selectAllWithCreatedBy(user.getUserId());
    }

    @SneakyThrows
    private List<ResourceTreeVO> getResourceTrees(List<Resource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return Lists.newArrayList();
        }

        HashMap<Long/* parentId */, List<Resource>> map = Maps.newHashMap();
        for (Resource resource : resources) {
            if (map.containsKey(resource.getParentId())) {
                map.get(resource.getParentId()).add(resource);
            } else {
                List<Resource> resourceList = Lists.newArrayList();
                resourceList.add(resource);
                map.put(resource.getParentId(), resourceList);
            }
        }

        List<ResourceTreeVO> result = Lists.newArrayListWithCapacity(resources.size());
        for (Resource resource : resources) {
            ResourceTreeVO resourceTree = AssembleUtil.to(resource, ResourceTreeVO.class);
            if (map.containsKey(resourceTree.getId())) {
                resourceTree.setChildren(map.get(resourceTree.getId()));
            } else {
                resourceTree.setChildren(Lists.newArrayList());
            }

            if (resourceTree.getLevel() == 1) {
                result.add(resourceTree);
            }
        }

        return result;
    }

    @SneakyThrows
    private List<MenuTreeVO> getMenuTrees(List<Menu> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }

        HashMap<Long/* parentId */, List<Menu>> map = Maps.newHashMap();
        for (Menu menu : menus) {
            if (map.containsKey(menu.getParentId())) {
                map.get(menu.getParentId()).add(menu);
            } else {
                List<Menu> menuList = Lists.newArrayList();
                menuList.add(menu);
                map.put(menu.getParentId(), menuList);
            }
        }

        List<MenuTreeVO> result = Lists.newArrayListWithCapacity(menus.size());
        for (Menu menu : menus) {
            MenuTreeVO menuTree = AssembleUtil.to(menu, MenuTreeVO.class);
            if (map.containsKey(menuTree.getId())) {
                menuTree.setChildren(map.get(menuTree.getId()));
            } else {
                menuTree.setChildren(Lists.newArrayList());
            }

            if (menuTree.getLevel() == 1) {
                result.add(menuTree);
            }
        }

        return result;
    }

    @SneakyThrows
    private PageResponse<UserInfoVO> getUserInfoPageResponse(PageResponse<Account> page) {
        if (CollectionUtils.isEmpty(page.getList())) {
            return new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), Lists.newArrayList());
        }

        List<Account> accounts = page.getList();
        List<Long> roleIds = Lists.newArrayListWithCapacity(accounts.size());
        Map<Long/* roleId */, List<UserInfoVO>> userInfoMap = Maps.newHashMap();
        for (Account account : accounts) {
            roleIds.add(account.getRoleId());
            if (userInfoMap.containsKey(account.getRoleId())) {
                userInfoMap.get(account.getRoleId()).add(AssembleUtil.to(account, UserInfoVO.class));
            } else {
                ArrayList<UserInfoVO> userInfos = Lists.newArrayList();
                userInfos.add(AssembleUtil.to(account, UserInfoVO.class));
                userInfoMap.put(account.getRoleId(), userInfos);
            }
        }

        AuthTokenVO user = UserContext.getUser();
        List<Role> roles = isAdmin(user) ? this.roleService.select(roleIds) : this.roleService.selectWithCreatedBy(roleIds, user.getUserId());
        for (Role role : roles) {
            for (UserInfoVO userInfo : userInfoMap.get(role.getId())) {
                userInfo.setRoleName(role.getName());
                userInfo.setRoleName(role.getName());
                userInfo.setRoleId(role.getId());
                userInfo.setType(role.getType());
            }
        }

        List<UserInfoVO> result = Lists.newArrayListWithCapacity(accounts.size());
        for (List<UserInfoVO> userInfos : userInfoMap.values()) {
            result.addAll(userInfos);
        }

        return new PageResponse<>(page.getPageNum(), page.getPageSize(), page.getTotal(), result);
    }

    private boolean isAdmin(AuthTokenVO user) {
        return user != null && "0".equals(user.getType());
    }
}
