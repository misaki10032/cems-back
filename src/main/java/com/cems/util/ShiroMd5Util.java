package com.cems.util;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/25 20:21
 * @Version 1.0
 */
public class ShiroMd5Util {
    /**
     * 加密操作
     *
     * @param username 使用账号作为盐值
     * @param password 要加密的密码
     * @return 返回加密后的密码
     */
    public static String toPwdMd5(String username, String password) {
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        int hashIterations = 1024;//哈希散列1024次
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toHex();
    }
}