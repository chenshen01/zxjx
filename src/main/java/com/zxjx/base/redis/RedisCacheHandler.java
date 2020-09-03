package com.zxjx.base.redis;

/**
 * description  处理RedisCache注解
 *
 * @author liuzhixiang 2020/08/23 11:16
 */

import com.alibaba.fastjson.JSON;
import com.zxjx.entity.Result;
import com.zxjx.utils.JsonUtils;
import com.zxjx.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@Aspect
@Component
public class RedisCacheHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.zxjx.base.redis.RedisCache)")
    public void redisCacheCut(){

    }

    @Around("redisCacheCut()")
    public Object handleRedisCache(ProceedingJoinPoint joinPoint)throws Throwable{
        String targetMethod = joinPoint.getSignature().getName();
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        // 是否标注在controller的方法上
        boolean targetOnController = joinPoint.getTarget().getClass().isAnnotationPresent(Controller.class) ||
                joinPoint.getTarget().getClass().isAnnotationPresent(RestController.class);
        // 是否标注在Service的方法上
        boolean targetOnService = joinPoint.getTarget().getClass().isAnnotationPresent(Service.class);
        for (Method method : methods) {
            // 获取执行的方法
            if (method.getName().equals(targetMethod)) {
                logger.debug("the target method is [{}]:" + targetMethod);
                RedisCache redisCache = method.getAnnotation(RedisCache.class);
                // 获取指定的key
                String key = redisCache.key();
                // 没有指定key使用当前的方法全名称 to-do
                // 获取返回值的类型
                Class<?> resultType = redisCache.resultType();
                ValueOperations operations = redisTemplate.opsForValue();
                if (!StringUtils.isEmpity(key)) {
                    // 指定了key的话使用指定的key
                    if (redisTemplate.hasKey(key)) {
                        String values = operations.get(key, 0, -1);
                        logger.debug("查询出来的值为：" + values);
                        Object result = JsonUtils.parseJsonString(values, resultType);
                        return getResult(targetOnController,targetOnService,result);
                    }  else {
                        // 执行目标方法
                        Object proceed = joinPoint.proceed();
                        if (proceed == null) {
                            throw new RuntimeException("没有值可以缓存");
                        }
                        String jsonString = "";
                        // 加在controller
                        if (proceed instanceof Result) {
                            jsonString = JSON.toJSONString(((Result) proceed).getResult());
                        } else {
                            // 加在service上
                            jsonString = JSON.toJSONString(proceed);
                        }
                        operations.set(key,jsonString);
                        logger.debug("增加了" + key + "的缓存" + jsonString);
                        return proceed;
                    }
                }
            }
        }
        throw new NoSuchMethodException();
    }

    private static Object getResult(boolean targetOnController,boolean targetOnService,Object result){
        if (targetOnController) {
            return Result.builder().result(result).build();
        }
        if (targetOnService) {
            return result;
        }
        return null;
    }

}
