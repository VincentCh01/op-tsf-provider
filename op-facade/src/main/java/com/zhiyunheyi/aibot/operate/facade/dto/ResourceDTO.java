package com.zhiyunheyi.aibot.operate.facade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

@Data
public class ResourceDTO extends Entity {

    private String name = "";

    private String url = "";

    private String owner = "";

    private String type = "";

    private Integer level;

    private Integer order;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long parentId;

    private String desc = "";

    private String extend = "";
}
