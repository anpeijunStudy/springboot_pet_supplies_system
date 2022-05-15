package com.cn;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan // 扫描过滤器
@EnableTransactionManagement // 开始事务注解
//@EnableCaching // 开启缓存
@EnableCreateCacheAnnotation // 开启jetCache缓存
@EnableMethodCache(basePackages = "com.cn") //开启方法注解缓存
public class SpringbootPetSuppliesSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPetSuppliesSystemApplication.class, args);
        log.info("宠物管理系统启动成功");
    }

}
