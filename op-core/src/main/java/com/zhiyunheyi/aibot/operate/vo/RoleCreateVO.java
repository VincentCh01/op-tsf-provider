package com.zhiyunheyi.aibot.operate.vo;

import com.zhiyunheyi.aibot.domain.core.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @menu:
 * @ClassName: RoleCreateVO
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 9:35
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class RoleCreateVO extends BaseEntity {
    private String name = "";

    private String type = "";

    private String desc = "";

    private List<Long> menuIds;

    private List<Long> resourceIds;
}
