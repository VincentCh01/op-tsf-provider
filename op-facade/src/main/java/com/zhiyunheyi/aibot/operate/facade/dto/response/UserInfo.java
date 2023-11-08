package com.zhiyunheyi.aibot.operate.facade.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyunheyi.aibot.operate.core.entity.BaseEntity;
import com.zhiyunheyi.aibot.domain.core.utils.Long2StringSerialize;
import lombok.Data;

/**
 * @menu:
 * @ClassName: UserInfo
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 16:44
 * @Version: 1.0.0-SNAPSHOT
 */
@Data
public class UserInfo extends BaseEntity {

    private String department = "";

    private String nickname = "";

    private String type = "";

    private String mobile = "";

    @JsonSerialize(using = Long2StringSerialize.class)
    private Long roleId;

    private String roleName = "";
}
