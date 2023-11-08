package com.zhiyunheyi.aibot.operate.facade.dto.request;

import lombok.Data;

@Data
public class AccountCondition {
    private String account = "";

    private String email = "";

    private String mobile = "";

    private String nickname = "";

    private String department = "";
}
