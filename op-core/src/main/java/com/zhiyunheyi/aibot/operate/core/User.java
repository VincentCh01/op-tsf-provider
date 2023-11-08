package com.zhiyunheyi.aibot.operate.core;

import com.zhiyunheyi.aibot.domain.core.Entity;
import lombok.Data;

/**
 * @menu:
 * @ClassName: User
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:13
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class User extends Entity {

    /**
     * 真实姓名
     */
    private String nickName = "";

    /**
     * 真实姓名
     */
    private String realName = "";

    /**
     * 头像
     */
    private String avatar = "";

    /**
     * 手机号
     */
    private String mobile = "";

    /**
     * 邮箱
     */
    private String email = "";

    /**
     * 性别.0-MALE 1-FEMALE
     */
    private int gender;
}
