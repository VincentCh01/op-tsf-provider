package com.zhiyunheyi.aibot.operate.facade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.Entity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: RoleMenuDTO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 13:42
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class RoleMenuDTO extends Entity {

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long menuId;

}
