package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.SysAdminInfoBig;
import com.cems.pojo.SysShenSu;
import com.cems.pojo.to.LevelUpDTO;
import com.cems.pojo.SysUpgrade;
import com.cems.pojo.to.PageTo;
import com.cems.service.SysAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("web")
public class SysAdminController {
    @Autowired
    SysAdminService sysAdminService;

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
    public String ShenSuOk(@PathVariable Integer id, @PathVariable String adminNum) {
        sysAdminService.shensuOK(adminNum);
        sysAdminService.delSS(id);
        return "ok";
    }

    @PostMapping("addlevelup")
    public String addLevelUp(@RequestBody LevelUpDTO level) {
        try {
            System.err.println(level);
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
    public String KillUser(Integer rowid, String rowstatus) {
        try {
            if (rowstatus.equals("正常")) {
                rowstatus = "正常";
            } else {
                rowstatus = "封禁";
            }
            sysAdminService.killAdmin(rowid, rowstatus);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
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
            System.out.println(entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }
    @GetMapping("UpOk")
    public String UpOk(Integer id, String status) {
        SysUpgrade adminUp = sysAdminService.getAdminUp((Integer) id);
        String jiu = adminUp.getAdminNow();
        String xin = adminUp.getAdminTarget();
        Map<String, Object> map = new HashMap<>();
        if (status.equals("已处理")) {
            map.put("id", adminUp.getAdminId());
            map.put("level", xin);
            map.put("upid", id);
            map.put("status", "已处理");
            sysAdminService.changeUpStatus(map);
            sysAdminService.upAdminOk(map);
        } else {
            map.put("id", adminUp.getAdminId());
            map.put("level", jiu);
            map.put("upid", id);
            map.put("status", "未处理");
            sysAdminService.changeUpStatus(map);
            sysAdminService.upAdminOk(map);
        }
        return "";
    }
    }
