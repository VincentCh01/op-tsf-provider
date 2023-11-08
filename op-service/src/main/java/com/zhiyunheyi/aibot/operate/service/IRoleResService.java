package com.zhiyunheyi.aibot.operate.service;

import com.zhiyunheyi.aibot.operate.core.RoleRes;

import java.util.List;

public interface IRoleResService {
    int insert(List<RoleRes> roleResList);

    int delete(List<String> ids);

    int deleteByRoleId(List<Long> roleIds);

    int deleteByResId(List<Long> resIds);

    int deleteByRoleAndRes(String roleId, List<String> resIds);

    List<RoleRes> query(RoleRes roleRes);

    List<RoleRes> queryWithCreatedBy(RoleRes roleRes, Long createdBy);

    List<RoleRes> selectByRoleId(Long roleId);

    List<RoleRes> selectByRoleIdAndCreatedBy(Long roleId, Long createdBy);
}
