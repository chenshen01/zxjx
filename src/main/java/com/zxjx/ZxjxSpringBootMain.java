package com.zxjx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 15:52
 */
@SpringBootApplication
// @MapperScan("com.zxjx.mapper")
@EnableAspectJAutoProxy
public class ZxjxSpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(ZxjxSpringBootMain.class,args);
    }

}
