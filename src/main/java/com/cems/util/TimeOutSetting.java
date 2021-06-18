package com.cems.util;

import org.springframework.stereotype.Component;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/25 20:21
 * @Version 1.0
 */
@Component
public class TimeOutSetting {
    //redis key过期时间(秒)
    public static int REDIS_TIME_OUT = 1800;

    public static int TIME_OUT_30 = 1800;

    public static int TIME_OUT_5 = 60 * 5;

    public static int TIME_OUT_3 = 60 * 3;

    public static int TIME_OUT_HOUR_1 = 60 * 60;

    public static int TIME_OUT_HOUR_6 = 60 * 60 * 6;

    public static int TIME_OUT_HOUR_24 = 60 * 60 * 24;

}
