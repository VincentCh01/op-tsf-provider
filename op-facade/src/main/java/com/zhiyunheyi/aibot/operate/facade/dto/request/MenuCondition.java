package com.zhiyunheyi.aibot.operate.facade.dto.request;

import lombok.Data;

/**
 * @menu:
 * @ClassName: MenuCondition
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 11:59
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class MenuCondition {
    private String name = "";

    private String type = "";

    private Integer level;

    private String parentId = "";
}
