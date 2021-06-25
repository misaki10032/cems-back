package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.*;
import com.cems.pojo.to.Appeal;
import com.cems.pojo.to.LevelUpDTO;
import com.cems.pojo.to.PageTo;
import com.cems.service.SysAdminService;
import com.cems.util.OperateUtil;
import com.cems.util.ShiroMd5Util;
import com.cems.util.EmilUtil;
import com.cems.util.IDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("web")
public class SysAdminController {
    @Autowired
    SysAdminService sysAdminService;
    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("getAllAppeal/{pageNum}/{pageSize}")
    public String getAllAppeal(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysShenSu> entList = new PageInfo(sysAdminService.selAllAppeal());
            List<SysShenSu> shensuList = entList.getList();
            map.put("data", shensuList);
            map.put("code", "200");
            map.put("total", entList.getTotal());
            System.out.println(entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("ShenSuOk/{id}/{adminNum}")
    public String ShenSuOk(@PathVariable Integer id, @PathVariable String adminNum, HttpSession session) {
        sysAdminService.shensuOK(adminNum);
        sysAdminService.delSS(id);
        OperateUtil.addOperate(session, sysAdminService);
        return "ok";
    }

    @PostMapping("addlevelup")
    public String addLevelUp(@RequestBody LevelUpDTO level) {
        try {
            sysAdminService.addLevelUp(level);
            return "200";
        } catch (Exception e) {
            return "500";
        }
    }

    @PostMapping("getAllAdminInfo/{pageNum}/{pageSize}")
    public String getAllAdminInfo(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysAdminInfoBig> sysAdminInfoBigPageInfo = new PageInfo<>(sysAdminService.getAllAdminInfo());
            List<SysAdminInfoBig> sysAdminInfoBigs = sysAdminInfoBigPageInfo.getList();
            map.put("data", sysAdminInfoBigs);
            map.put("code", "200");
            map.put("total", sysAdminInfoBigPageInfo.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("killAdmin")
    public String KillUser(Integer rowid, String rowstatus, HttpSession session) {
        try {
            if (rowstatus.equals("正常")) {
                rowstatus = "正常";
            } else {
                rowstatus = "封禁";
            }
            sysAdminService.killAdmin(rowid, rowstatus);
            OperateUtil.addOperate(session, sysAdminService);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
    }

    @PostMapping("selAllUp/{pageNum}/{pageSize}")
    public String selAllUp(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysUpgrade> entList = new PageInfo(sysAdminService.selAllUp());
            List<SysUpgrade> upList = entList.getList();
            map.put("data", upList);
            map.put("code", "200");
            map.put("total", entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("UpOk")
    public String UpOk(Integer id, String status,HttpSession session) {
        SysUpgrade adminUp = sysAdminService.getAdminUp((Integer) id);
        String jiu = adminUp.getAdminNow();
        String xin = adminUp.getAdminTarget();
        Map<String, Object> map = new HashMap<>();
        if (status.equals("已处理")) {
            map.put("id", adminUp.getAdminId());
            map.put("level", xin);
            map.put("adminlevel", xin);
            map.put("jiu", jiu);
            map.put("upid", id);
            map.put("status", "已处理");
            OperateUtil.addOperate(session, sysAdminService);
            sysAdminService.changeUpStatus(map);
            sysAdminService.upAdminOk(map);
        } else {
            map.put("id", adminUp.getAdminId());
            map.put("adminlevel", jiu);
            map.put("level", xin);
            map.put("upid", id);
            map.put("jiu", jiu);
            map.put("status", "未处理");
            OperateUtil.addOperate(session, sysAdminService);
            sysAdminService.changeUpStatus(map);
            sysAdminService.upAdminOk(map);
        }
        return "";
    }

    @PostMapping("changePwd")
    public String changePwd(String msg) {
        return "200";
    }

    @PostMapping("AdminByNum/{num}")
    public SysAdminInfo AdminByNum(@PathVariable String num) {
        return sysAdminService.getAdminInfo(num);
    }

    @PostMapping("updateAdminByNum")
    public String updateAdminByNum(@RequestBody SysAdminInfo sysAdminInfo, HttpSession session) {
        try {
            sysAdminService.updateAdminInfo(sysAdminInfo);
            OperateUtil.addOperate(session, sysAdminService);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
    }

    @GetMapping("delAllUp")
    public String delAllUp(String status,HttpSession session){
        int i = sysAdminService.delAllUp(status);
        if (i==0){
            return "没有要清空的数据了";
        }
        OperateUtil.addOperate(session, sysAdminService);
        return "清空成功";
    }



    /**
     * 管理员 更改密码
     *
     * @param appeal
     * @return
     */
    @PostMapping("changePassword")
    public String changePassword(@RequestBody Appeal appeal) {
        System.err.println(appeal);
        String psw1 = ShiroMd5Util.toPwdMd5(appeal.getPhone(), appeal.getPsw());
        String psw2 = ShiroMd5Util.toPwdMd5(appeal.getPhone(), appeal.getPass());
        Map<String, Object> map = new HashMap<>();
        //账号
        map.put("phone", appeal.getPhone());
        //旧密码
        map.put("psw", psw1);
        //新密码
        map.put("pass", psw2);
        SysAdmin sysAdmin = sysAdminService.gljudgeAP(map);
        if (sysAdmin != null) {
            sysAdminService.changePassword(map);
            System.out.println("1212313123123123");
            return "200";

        } else {
            return "502";
        }
    }


}
