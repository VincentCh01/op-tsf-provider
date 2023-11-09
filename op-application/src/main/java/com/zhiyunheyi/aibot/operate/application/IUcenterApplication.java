package com.zhiyunheyi.aibot.operate.application;

import com.zhiyunheyi.aibot.domain.core.PageResponse;
import com.zhiyunheyi.aibot.operate.core.Account;
import com.zhiyunheyi.aibot.operate.core.Menu;
import com.zhiyunheyi.aibot.operate.core.Resource;
import com.zhiyunheyi.aibot.operate.core.Role;
import com.zhiyunheyi.aibot.operate.vo.AccountConditionVO;
import com.zhiyunheyi.aibot.operate.vo.AccountQueryVO;
import com.zhiyunheyi.aibot.operate.vo.MenuTreeVO;
import com.zhiyunheyi.aibot.operate.vo.ResourceTreeVO;
import com.zhiyunheyi.aibot.operate.vo.RoleConditionVO;
import com.zhiyunheyi.aibot.operate.vo.RoleCreateVO;
import com.zhiyunheyi.aibot.operate.vo.UserInfoVO;

import java.util.List;

/**
 * @menu:
 * @ClassName: IUcenterApp
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 9:48
 * @Version: 1.0.0-SNAPSHOT
 */
public interface IUcenterApplication {
    int insert(Account account);

    int update(Account request);

    int delete(List<Long> ids);

    UserInfoVO getByUserId(Long userId);

    UserInfoVO getByMobile(String mobile);

    PageResponse<UserInfoVO> page(AccountConditionVO condition, Integer pageNo, Integer pageSize);

    PageResponse<UserInfoVO> pageByKey(AccountQueryVO condition, Integer pageNo, Integer pageSize);

    int insertRole(RoleCreateVO req);

    int updateRole(RoleCreateVO req);

    int deleteRole(List<Long> ids);

    PageResponse<Role> pageRole(RoleConditionVO condition, Integer pageNo, Integer pageSize);

    List<ResourceTreeVO> treeAll();

    List<ResourceTreeVO> treeByRoleId(Long roleId);

    List<Resource> selectByRoleId(Long roleId);

    List<Resource> selectByRoleIdAndUserId(Long roleId);

    List<Resource> selectAllResource();

    List<Role> selectAllRole();

    List<MenuTreeVO> treeAllMenu();

    List<MenuTreeVO> treeMenuByRoleId(Long roleId);

    List<Menu> selectMenuByRoleId(Long roleId);

    List<Menu> selectAllMenu();
}
