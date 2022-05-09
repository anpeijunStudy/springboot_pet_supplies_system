package com.cn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringbootPetSuppliesSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPetSuppliesSystemApplication.class, args);
        log.info("宠物管理系统启动成功");
    }

}
