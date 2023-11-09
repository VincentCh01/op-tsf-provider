package com.zhiyunheyi.aibot.operate.vo;

import lombok.Data;

/**
 * @menu:
 * @ClassName: UserCondtionVO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 9:37
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class UserConditionVO {
    /**
     * 真实姓名
     */
    private String nickName = "";

    /**
     * 真实姓名
     */
    private String realName = "";

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

    /**
     * 部门
     */
    private String department = "";
}
