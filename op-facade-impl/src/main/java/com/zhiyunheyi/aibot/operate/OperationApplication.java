package com.zhiyunheyi.aibot.operate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @menu:
 * @ClassName: OperationApplication
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/11/9 14:02
 * @Version: 1.0.0-SNAPSHOT
 */
@MapperScan("com.zhiyunheyi.aibot.operate.repository.impl.sql")
@SpringBootApplication
public class OperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class, args);
    }
}
