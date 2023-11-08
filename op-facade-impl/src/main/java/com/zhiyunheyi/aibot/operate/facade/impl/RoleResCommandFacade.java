package com.zhiyunheyi.aibot.operate.facade.impl;

import com.zhiyunheyi.aibot.operate.facade.IRoleResCommandFacade;
import com.zhiyunheyi.aibot.userservice.operate.service.RoleResService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @menu:
 * @ClassName: RoleResCommandFacade
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/26 14:39
 * @Version: 1.0.0-SNAPSHOT
 */
@RestController
public class RoleResCommandFacade implements IRoleResCommandFacade {

    @Resource
    private RoleResService service;
}
