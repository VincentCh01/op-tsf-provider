package com.zhiyunheyi.aibot.operate.facade.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.operate.core.dto.ResourceDTO;
import com.zhiyunheyi.aibot.operate.core.entity.BaseEntity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

import java.util.List;

/**
 * @menu:
 * @ClassName: ResourceTree
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 18:19
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class ResourceTree extends BaseEntity {

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

    private List<ResourceDTO> children;
}
