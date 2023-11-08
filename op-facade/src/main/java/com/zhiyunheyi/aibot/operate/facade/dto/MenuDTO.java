package com.zhiyunheyi.aibot.operate.facade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: MenuDTO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:42
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class MenuDTO extends Entity {

    private String name = "";

    private String url = "";

    private String type = "";

    private Integer level;

    private Integer order;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long parentId;

    private String desc = "";
}
