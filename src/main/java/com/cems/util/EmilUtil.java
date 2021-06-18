package com.cems.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/25 20:21
 * @Version 1.0
 */
public class EmilUtil {
    @Autowired
    private static JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     *
     * @param title 邮件标题
     * @param Text  邮件内容
     * @param to    发送给的对象
     */
    public static void sendEmal(String title, String Text, String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(title);
        mailMessage.setText(Text);
        mailMessage.setTo(to);
        mailMessage.setFrom("fcms_snut@qq.com");
        mailSender.send(mailMessage);
    }
}
