package com.zhiyunheyi.aibot.operate.facade.dto.request;

/**
 * @menu:
 * @ClassName: RoleCreateReq
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 17:43
 * @Version: 1.0.0-SNAPSHOT
 */

import com.zhiyunheyi.aibot.operate.core.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class RoleCreateReq extends BaseEntity {

    private String name = "";

    private String type = "";

    private String desc = "";

    private List<Long> menuIds;

    private List<Long> resourceIds;
}
