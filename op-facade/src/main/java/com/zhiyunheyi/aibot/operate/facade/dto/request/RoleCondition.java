package com.zhiyunheyi.aibot.operate.facade.dto.request;

import lombok.Data;

/**
 * @menu:
 * @ClassName: RoleCondition
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 11:03
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class RoleCondition {

    private String owner = "";

    private String name = "";

    private String type = "";

    private Integer weight;

    private String desc = "";

}
