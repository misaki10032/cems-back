package com.cems.controller.web;
import com.alibaba.fastjson.JSON;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfoBig;
import com.cems.pojo.SysAdminSuc;
import com.cems.pojo.to.Appeal;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginAdmin;
import com.cems.service.ComUserService;
import com.cems.service.SysAdminService;
import com.cems.util.IDUtil;
import com.cems.util.JWTUtil;
import com.cems.util.ShiroMd5Util;
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
import java.util.List;
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
    @Autowired
    JavaMailSenderImpl mailSender;
    @PostMapping("userlogin")
    public String login(@RequestBody LoginAdmin json){
        System.out.println(json);
        String num = json.getNum();
        String pwd = json.getPwd();
        //传递给shiro
        Subject subject = SecurityUtils.getSubject();//获取用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(num, pwd);//封装
        Session session = subject.getSession();
        try {
            session.setAttribute("isAdmin", true);//设置非管理员
            subject.login(token);//通过subject取
            SysAdmin logingAdmin = (SysAdmin) session.getAttribute("LogingAdmin");
            String admintoken = JWTUtil.getToken(logingAdmin);
            HashMap<String, Object> map = new HashMap<>();
            map.put("adminToken", admintoken);
            map.put("adminName", num);
            map.put("adminLevel", logingAdmin.getAdminLevel());
            map.put("adminId", logingAdmin.getId());
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
        }finally {
            SysAdmin admin = (SysAdmin) session.getAttribute("LogingAdmin");
            SysAdminSuc sysAdminSuc = sysAdminService.selOneSysSuc(admin.getId());
            Map<String,Object> map = new HashMap<>();
            map.put("adminId",admin.getId());
            map.put("loginNum",sysAdminSuc.getLoginNum()+1);
            sysAdminService.setSysLoginGraph(map);
        }
    }
    /**用户申诉*/
    @PostMapping("userAppeal")
    public String appeal1(@RequestBody Appeal appeal){
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
        String s = ShiroMd5Util.toPwdMd5(appeal.getPhone(), appeal.getPsw());
        Map<String,Object> map = new HashMap<>();
        map.put("phone",appeal.getPhone());
        map.put("psw", s);
        map.put("desc", appeal.getDesc());
        SysAdmin sysAdmin = sysAdminService.gljudgeAP(map);
        if (sysAdmin!=null){
            if (sysAdmin.getAdminStatus().equals("正常")) {
                return "405";
            } else {
                sysAdminService.insApple(map);
                return "1";
            }
        }else {
            return "502";
        }
    }
    /**管理员注册*/
    @PostMapping("register")
    public String register(@RequestBody Appeal appeal){
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
            SysAdmin sysAdmin = sysAdminService.selOneSysZC(appeal.getAcc());
            map.put("id", sysAdmin.getId());
            sysAdminService.insertEmail(map);
            sysAdminService.insertSysSuc(sysAdmin.getId());
            return "200";
        }
    }

    @GetMapping("forgetPsw")
    public String forgetPsw(String acc, String email) {
        System.out.println("acc===="+acc);
        System.out.println("email===="+email);
        Map<String,Object> map = new HashMap<>();
        map.put("num",acc);
        map.put("email",email);
        List<SysAdminInfoBig> sysAdminInfoBigs = sysAdminService.selByEmailId(map);
        if (sysAdminInfoBigs.size()==0){
            return "203";
        }else {
            String msg = IDUtil.getID().substring(0, 6);
            String title = "【验证码】";
            String text = "【CEMS校园服务平台】您的验证码为: " + msg + "，3分钟以内有效!";
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(title);
            mailMessage.setText(text);
            mailMessage.setTo(email);
            mailMessage.setFrom("fcms_snut@qq.com");
            mailSender.send(mailMessage);
            return msg;
        }
    }
    /**管理员忘记密码*/
    @PostMapping("forgetPswOk")
    public String forgetPswOk(@RequestBody Appeal appeal){
        String s = ShiroMd5Util.toPwdMd5(appeal.getAcc(), appeal.getNewPsw());
        Map<String,Object> map = new HashMap<>();
        map.put("num",appeal.getAcc());
        map.put("email",appeal.getEmail());
        List<SysAdminInfoBig> sysAdminInfoBigs = sysAdminService.selByEmailId(map);
        if (sysAdminInfoBigs.size()==0){
            return "402";
        }
        map.put("pwd",s);
        map.put("num", appeal.getAcc());
        int i = sysAdminService.forgetPswOk(map);
        return "找回密码成功";
    }

}
