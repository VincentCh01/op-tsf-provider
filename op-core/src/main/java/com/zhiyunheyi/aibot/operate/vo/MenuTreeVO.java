package com.zhiyunheyi.aibot.operate.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.domain.core.BaseEntity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import com.zhiyunheyi.aibot.operate.core.Menu;
import lombok.Data;

import java.util.List;


/**
 * @menu:
 * @ClassName: MenuTree
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/2 14:05
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class MenuTreeVO extends BaseEntity {

    private String name = "";

    private String url = "";

    private String type = "";

    private Integer level;

    private Integer order;

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long parentId;

    private String desc = "";

    private List<Menu> children;
}
