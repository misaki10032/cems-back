package com.cems.controller.uniApp.login;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginAdmin;
import com.cems.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName LoginController
 * @Author 陈新予(blank)
 * @Date 2021/6/25
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class LoginController {

    @PostMapping("uniAppLogin")
    public String UserLogin(@RequestBody LoginAdmin json) {
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
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", userToken);
            map.put("loginUser", loginUser);
            map.put("pName", loginUser.getUserPname());
            map.put("userId", loginUser.getId());
            String s = JSON.toJSONString(map);
            System.out.println(s);
            return s;
        } catch (UnknownAccountException uae) {
            return "404";
        } catch (IncorrectCredentialsException ice) {
            return "502";
        } catch (LockedAccountException lae) {
            return "403";
        } catch (Exception e) {
            return "500";
        }
    }

}
