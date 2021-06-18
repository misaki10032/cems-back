package com.cems.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SelectRedis
 * @Author 陈新予(blank)
 * @Date 2021/4/30
 * @Version 1.0
 */
@Component
@SuppressWarnings("all")
public class SelectRedis<T> {
    /**
     * redis查询列表的泛用抽象
     *
     * @param keyName    要查询的keyName
     * @param mapper     mapper
     * @param invokeName mapper的方法
     * @return 返回object类型的List集合
     */
    public static List<Object> selectRedis(RedisUtil redisUtil, String keyName, Object mapper, String invokeName)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        try {
            List<Object> redisFic = redisUtil.lRange(keyName, 0, -1);
            if (!redisFic.isEmpty()) {
                return redisFic;
            } else {
                Method method = mapper.getClass().getDeclaredMethod(invokeName);
                List<Object> invoke = (List<Object>) (method.invoke(mapper));
                for (Object fiction : invoke) {
                    redisUtil.lLeftPush(keyName, fiction);
                }
                redisUtil.expire(keyName, TimeOutSetting.REDIS_TIME_OUT, TimeUnit.SECONDS);
                return invoke;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return (List<Object>) (mapper.getClass().getDeclaredMethod(invokeName).invoke(mapper));
        }
    }

    /**
     * @param keyName    要查询的keyName
     * @param mapper     mapper
     * @param invokeName mapper的方法
     * @param msg        可变长度参数,String类型
     * @param time       过期时间
     * @return 返回object类型的List集合
     */
    public static List<Object> selectRedis(RedisUtil redisUtil, String keyName, Object mapper, String invokeName, Object msg, int time)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            List<Object> redisFic = redisUtil.lRange(keyName, 0, -1);
            if (!redisFic.isEmpty()) {
                return redisFic;
            } else {
                Method method = mapper.getClass().getDeclaredMethod(invokeName, String.class);
                List<Object> invoke = (List<Object>) (method.invoke(mapper, msg));
                for (Object fiction : invoke) {
                    redisUtil.lLeftPush(keyName, fiction);
                }
                redisUtil.expire(keyName, time, TimeUnit.SECONDS);
                return invoke;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return (List<Object>) (mapper.getClass().getDeclaredMethod(invokeName).invoke(mapper, msg));
        }

    }

    /**
     * @param keyName    要查询的keyName
     * @param mapper     mapper
     * @param invokeName mapper的方法
     * @param msg        可变长度参数,String类型
     * @return 返回object类型的具体对象
     */
    public static Object selectRedisOne(RedisUtil redisUtil, String keyName, Object mapper, String invokeName, String msg, int time)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try {
            Object redisFic = redisUtil.get(keyName);
            if (redisFic != null) {
                return redisFic;
            } else {
                Method method = mapper.getClass().getDeclaredMethod(invokeName, String.class);
                Object invoke = method.invoke(mapper, msg);
                redisUtil.set(keyName, invoke);
                redisUtil.expire(keyName, time, TimeUnit.SECONDS);
                return invoke;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mapper.getClass().getDeclaredMethod(invokeName).invoke(mapper, msg);
        }

    }
}
