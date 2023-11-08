package com.zhiyunheyi.aibot.operate.facade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

@Data
public class RoleResDTO extends Entity {

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long resId;
}
