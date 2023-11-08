package com.zhiyunheyi.aibot.operate.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: RoleRes
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:40
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class RoleRes extends Entity {

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long resId;
}
