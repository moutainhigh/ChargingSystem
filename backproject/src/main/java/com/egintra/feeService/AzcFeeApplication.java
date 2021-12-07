package com.egintra.feeService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.egintra.common.repository.mapper")
@ComponentScan(basePackages = "com.egintra")
@EnableScheduling
public class AzcFeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzcFeeApplication.class, args);
    }
}
