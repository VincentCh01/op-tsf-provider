package com.zhiyunheyi.aibot.operate.vo;

import lombok.Data;

/**
 * @menu:
 * @ClassName: ResourceConditionVo
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 9:35
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class ResourceConditionVO {
    private String name = "";

    private String type = "";

    private Integer level;

    private String parentId = "";
}
