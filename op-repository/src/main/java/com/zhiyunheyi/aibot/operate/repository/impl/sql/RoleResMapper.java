package com.zhiyunheyi.aibot.operate.repository.impl.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiyunheyi.aibot.operate.core.RoleRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @menu:
 * @ClassName: RoleResMapper
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:24
 * @Version: 1.0.0-SNAPSHOT
 */
public interface RoleResMapper extends BaseMapper<RoleRes> {

    int insert(@Param("list") List<RoleRes> roleResList);

    int delete(@Param("ids") List<String> ids);

    int deleteByRoleId(@Param("roleIds") List<Long> roleId);

    int deleteByResId(@Param("resIds") List<Long> resId);

    int deleteByRoleAndRes(@Param("roleId") String roleId, @Param("resIds") List<String> resIds);

    List<RoleRes> query(@Param("roleRes") RoleRes roleRes);

    List<RoleRes> queryWithCreatedBy(@Param("roleRes") RoleRes roleRes, @Param("createdBy") Long createdBy);

    List<RoleRes> selectByRoleId(@Param("roleId") Long roleId);

    List<RoleRes> selectByRoleIdAndCreatedBy(@Param("roleId") Long roleId, @Param("createdBy") Long createdBy);
}
