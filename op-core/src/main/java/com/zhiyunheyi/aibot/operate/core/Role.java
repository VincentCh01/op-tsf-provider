package com.zhiyunheyi.aibot.operate.core;

import com.zhiyunheyi.aibot.domain.core.Entity;
import lombok.Data;

/**
 * @menu:
 * @ClassName: Role
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:40
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class Role extends Entity {

    private String owner = "10000";

    private String name = "";

    private String type = "";

    private Integer weight = 0;

    private String desc = "";

}
