package com.zhiyunheyi.aibot.operate.vo;

import lombok.Data;

import java.util.List;

/**
 * @menu:
 * @ClassName: AuthTokenVO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 13:56
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class AuthTokenVO {
    private Long userId;

    private String type;

    private String salt;

    private Long merchantId;

    private String phone;

    private Long timestamp;

    private String token;

    private List<Long> roleIds;
}
