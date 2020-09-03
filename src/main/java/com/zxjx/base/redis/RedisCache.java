package com.zxjx.base.redis;

import java.lang.annotation.*;

/**
 * description  在方法上添加注解能使得该方法的数据能缓存到redis中，
 *              如果redis中有，则从redis中获取
 *
 * com.zxjx.base.redis.RedisCache
 *
 * @author liuzhixiang 2020/08/23 11:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    /**
     * 指定缓存的key
     */
    String key() default "";

    /**
     * 缓存的目标class文件
     */
    Class<?> resultType();
}
