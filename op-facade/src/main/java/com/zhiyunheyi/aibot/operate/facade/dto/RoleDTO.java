package com.zhiyunheyi.aibot.operate.facade.dto;

import com.zhiyunheyi.aibot.domain.core.Entity;
import lombok.Data;

@Data
public class RoleDTO extends Entity {

    private String owner = "";

    private String name = "";

    private String type = "";

    private Integer weight;

    private String desc = "";
}
