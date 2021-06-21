package com.cems.controller.web;
import com.alibaba.fastjson.JSON;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.to.Appeal;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginAdmin;
import com.cems.service.ComUserService;
import com.cems.service.SysAdminService;
import com.cems.util.JWTUtil;
import com.cems.util.ShiroMd5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginContorller
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
@RestController
@RequestMapping("web")
public class LoginContorller {
    @Autowired
    ComUserService comUserService;
    @Autowired
    SysAdminService sysAdminService;
    @PostMapping("userlogin")
    public String login(@RequestBody LoginAdmin json){
        System.out.println(json);
        String num = json.getNum();
        String pwd = json.getPwd();
        //传递给shiro
        Subject subject = SecurityUtils.getSubject();//获取用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(num, pwd);//封装
        try {
            subject.login(token);//通过subject取
            SysAdmin logingAdmin = (SysAdmin) subject.getSession().getAttribute("LogingAdmin");
            String admintoken = JWTUtil.getToken(logingAdmin);
            HashMap<String, Object> map = new HashMap<>();
            map.put("adminToken",admintoken);
            map.put("adminName",num);
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
    /**用户申诉*/
    @PostMapping("appeal1")
    public String appeal1(@RequestBody Appeal appeal){
        System.out.println("appeal======="+appeal);
        String s = ShiroMd5Util.toPwdMd5(appeal.getPhone(), appeal.getPsw());
        Map<String,Object> map = new HashMap<>();
        map.put("phone",appeal.getPhone());
        map.put("psw",s);
        ComUser comUser = comUserService.judgeAP(map);
        if (comUser!=null){
            if (comUser.getStatus().equals("正常")){
                return "405";
            }
            return "1";
        }else {
            return "502";
        }
    }

    /**管理员申诉*/
    @PostMapping("appeal")
    public String appeal(@RequestBody Appeal appeal){
        System.out.println("appeal======="+appeal);
        String s = ShiroMd5Util.toPwdMd5(appeal.getPhone(), appeal.getPsw());
        Map<String,Object> map = new HashMap<>();
        map.put("phone",appeal.getPhone());
        map.put("psw",s);
        SysAdmin sysAdmin = sysAdminService.gljudgeAP(map);
        if (sysAdmin!=null){
            if (sysAdmin.getAdminStatus().equals("正常")){
                return "405";
            }
            return "1";
        }else {
            return "502";
        }
    }
    /**管理员注册*/
    @PostMapping("register")
    public String register(@RequestBody Appeal appeal){
        System.out.println("appeal===="+appeal);
        String s = ShiroMd5Util.toPwdMd5(appeal.getAcc(), appeal.getPsw());
        Map<String,Object> map = new HashMap<>();
        map.put("acc",appeal.getAcc());
        map.put("psw",s);
        map.put("phoneTwo",appeal.getPhoneTwo());
        map.put("email",appeal.getEmail());
        if(sysAdminService.selOneSysByAcc(appeal.getAcc()).size()!= 0){
            return "401";
        }else if(sysAdminService.selOneSysByEP(map).size()!= 0){
            return "402";
        }else if(sysAdminService.selOneSysByEP2(map).size()!= 0){
            return "403";
        }else{
            sysAdminService.registerSys(map);
            sysAdminService.insertEmail(map);
            return "200";
        }
    }
}
