package com.zhiyunheyi.aibot.operate.facade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: AccountDTO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 16:09
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class AccountDTO extends Entity {
    /**
     * 用户id
     */
    @JsonSerialize(using = Long2StringSerialize.class)
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
    private String nickname = "";
    /**
     * 角色id.
     */
    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId = 0L;
    /**
     * 部门
     */
    private String department = "";
}
