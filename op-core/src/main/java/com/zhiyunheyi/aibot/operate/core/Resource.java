package com.zhiyunheyi.aibot.operate.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: Resource
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:41
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class Resource extends Entity {

    private String name = "";

    private String url = "";

    private String owner = "";

    private String type = "";

    private Integer level = 1;

    private Integer order = 1;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long parentId = 0L;

    private String desc = "";

    private String extend = "";

}
