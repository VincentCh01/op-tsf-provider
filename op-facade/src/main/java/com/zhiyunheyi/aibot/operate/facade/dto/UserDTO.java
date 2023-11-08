package com.zhiyunheyi.aibot.operate.facade.dto;

import com.zhiyunheyi.aibot.domain.core.Entity;
import lombok.Data;

@Data
public class UserDTO extends Entity {
    /**
     * 真实姓名
     */
    private String nickName="";

    /**
     * 真实姓名
     */
    private String realName="";

    /**
     * 头像
     */
    private String avatar="";

    /**
     * 手机号
     */
    private String mobile="";

    /**
     * 邮箱
     */
    private String email="";

    /**
     * 性别.0-MALE 1-FEMALE
     */
    private int gender;

    /**
     * 部门
     */
    private String department = "";
}
