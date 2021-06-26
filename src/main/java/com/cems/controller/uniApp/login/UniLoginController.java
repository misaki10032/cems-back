package com.cems.controller.uniApp.login;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginAdmin;
import com.cems.service.ComUserService;
import com.cems.util.IDUtil;
import com.cems.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LoginController
 * @Author 陈新予(blank)
 * @Date 2021/6/25
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class UniLoginController {
    @Autowired
    ComUserService userService;
    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("uniAppLogin")
    public Map<String, Object> UserLogin(@RequestBody LoginAdmin json) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(json);
        String num = json.getNum();
        String pwd = json.getPwd();
        //传递给shiro
        Subject subject = SecurityUtils.getSubject();//获取用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(num, pwd);//封装
        Session session = subject.getSession();
        try {
            session.setAttribute("isAdmin", false);//设置非管理员
            subject.login(token);//通过subject取
            ComUser loginUser = (ComUser) session.getAttribute("loginUser");
            String userToken = JWTUtil.getToken(loginUser);
            map.put("token", userToken);
            map.put("loginUser", loginUser);
            map.put("pName", loginUser.getUserPname());
            map.put("userId", loginUser.getId());
            map.put("code", "200");
        } catch (UnknownAccountException uae) {
            map.put("code", "500");
            map.put("msg", "账号未注册!");
        } catch (IncorrectCredentialsException ice) {
            map.put("code", "501");
            map.put("msg", "密码或账号错误!");
        } catch (LockedAccountException lae) {
            map.put("code", "502");
            map.put("msg", "账号被锁定!");
        } catch (Exception e) {
            map.put("code", "503");
            map.put("msg", "服务器异常!");
        }
        return map;
    }

    @PostMapping("userResiger")
    public Map<String, Object> userResiger(@RequestBody ComUser user) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            userService.userResiger(user);
            map.put("msg", "注册成功!");
            map.put("code", "200");
        } catch (Exception e) {
            map.put("msg", "服务器异常!");
            map.put("code", "500");
        }
        return map;
    }

    @GetMapping("getUserEmil")
    public Map<String, Object> getUserEmil(String email, String phone) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        ComUser userEmil = userService.getUserEmil(email, phone);
        if (userEmil != null) {
            map.put("code", "200");
            map.put("user", userEmil);
        } else {
            map.put("code", "500");
        }
        return map;
    }

    @GetMapping("sendEmil")
    public Map<String, Object> SendEmil(String email) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            String msg = IDUtil.getID().substring(0, 6);
            String title = "【验证码】";
            String text = "【CEMS校园服务平台】您的验证码为: " + msg + "，3分钟以内有效!";
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(title);
            mailMessage.setText(text);
            mailMessage.setTo(email);
            mailMessage.setFrom("fcms_snut@qq.com");
            mailSender.send(mailMessage);
            System.err.println("=============================成功发送=============================");
            map.put("code", "200");
            map.put("msg", msg);
        } catch (Exception e) {
            map.put("code", "500");
        }
        return map;
    }

    @GetMapping("updatePwd")
    public Map<String, Object> updateUserPwd(String id, String pwd) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {

            userService.updateUserPwd(Integer.parseInt(id), pwd);
            map.put("msg", "修改成功!");
            map.put("code", "200");
        } catch (Exception e) {
            map.put("code", "500");
        }
        return map;
    }

}
