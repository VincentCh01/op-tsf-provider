package com.zhiyunheyi.aibot.operate.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: Account
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:35
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class Account extends Entity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 邮箱
     */
    private String email = "";
    /**
     * 手机
     */
    private String mobile = "";
    /**
     * 密码
     */
    private String password = "";
    /**
     * 昵称
     */
    private String nickname= "";
    /**
     * 角色id.
     */
    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId;
    /**
     * 部门
     */
    private String department = "";
}
