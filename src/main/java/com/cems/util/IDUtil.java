package com.cems.util;

import java.util.UUID;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/25 20:21
 * @Version 1.0
 */
public class IDUtil {

    public static String getID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
