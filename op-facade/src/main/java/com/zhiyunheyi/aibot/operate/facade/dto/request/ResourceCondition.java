package com.zhiyunheyi.aibot.operate.facade.dto.request;

import lombok.Data;

/**
 * @menu:
 * @ClassName: ResourceCondition
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 11:11
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class ResourceCondition {

    private String name = "";

    private String type = "";

    private Integer level;

    private String parentId = "";

}
