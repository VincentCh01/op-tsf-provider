package com.zhiyunheyi.aibot.operate.facade.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @ResponseBody
    @GetMapping("")
    public Object health() {
        return "success";
    }
}
