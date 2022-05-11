package com.cn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan // 扫描过滤器
@EnableTransactionManagement // 开始事务注解
public class SpringbootPetSuppliesSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPetSuppliesSystemApplication.class, args);
        log.info("宠物管理系统启动成功");
    }

}
