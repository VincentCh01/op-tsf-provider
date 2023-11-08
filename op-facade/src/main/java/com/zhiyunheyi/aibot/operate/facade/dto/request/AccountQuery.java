package com.zhiyunheyi.aibot.operate.facade.dto.request;

import lombok.Data;

/**
 * @menu:
 * @ClassName: AccountQuery
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 17:16
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class AccountQuery {

    private String key = "";

    private String account = "";

    private String email = "";

    private String mobile = "";

    private String nickname = "";

    private String department = "";
}
