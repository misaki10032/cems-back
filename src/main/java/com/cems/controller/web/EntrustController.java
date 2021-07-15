package com.cems.controller.web;


import ch.qos.logback.core.pattern.color.MagentaCompositeConverter;
import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.CommentReply;
import com.cems.pojo.MoneyBack;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.PageTo;
import com.cems.pojo.to.PageToById;
import com.cems.pojo.to.SysEntrust;
import com.cems.service.ComEntrustService;
import com.cems.service.SysAdminService;
import com.cems.service.UserService;
import com.cems.util.OperateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EntrustController
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@RestController
@RequestMapping("web")
public class EntrustController {
    @Autowired
    ComEntrustService entrustService;
    @Autowired
    UserService userService;
    @Autowired
    SysAdminService adminService;

    @GetMapping("getAllEnts")
    public String getAllEnts() {
        List<ComEntrust> entrusts = entrustService.getEntrusts();
        String s = JSON.toJSONString(entrusts);
        return s;
    }

    @PostMapping("getEntlimit/{pageNum}/{pageSize}")
    public String getEntLimit(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getEntrusts());
            List<ComEntrust> entLimitList = entList.getList();
            map.put("data", entLimitList);
            map.put("code", "200");
            map.put("total", entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }

        return JSON.toJSONString(map);
    }

    @GetMapping("updataEntState")
    public String updataEntState(Integer rowid, String rowstatus, HttpSession session) {
        try {
            if (rowstatus.equals("已审核")) {
                rowstatus = "未审核";
            } else {
                rowstatus = "已审核";
            }
            entrustService.updataEntState(rowid, rowstatus);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * 获得所有的类型名称 以及对应的数量
     * @param pageTo
     * @return
     */
    @PostMapping("getEntTypelimit/{pageNum}/{pageSize}")
    public String getEntTypeLimit(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<ComEntrustType> entList = new PageInfo<>(entrustService.getEntTypes());
            List<ComEntrustType> entLimitList = entList.getList();
            map.put("data", entLimitList);
            map.put("code", "200");
            map.put("total", entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }
    @PostMapping("MoneyBack")
    public String MoneyBack(@RequestBody MoneyBack moneyBack) {
        Double money1 = null;
        Double money2 = null;
        String msg = "";
        if (moneyBack.getEntPlan().equals("已完成")) {
            msg += "ok";
            return msg;
        } else {
            Map<String, Object> map = new HashMap<>();
            ComUser comUser = userService.selOneUser(moneyBack.getEntConsignor());
            map.put("id", moneyBack.getEntConsignor());
            if (moneyBack.getEntAgent() != -1) {
                money2 = moneyBack.getEntMoney() * 0.9;
                map.put("userMoney", moneyBack.getEntMoney() * 0.9 + comUser.getUserMoney());
            } else {
                money2 = moneyBack.getEntMoney();
                map.put("userMoney", moneyBack.getEntMoney() + comUser.getUserMoney());
            }
            entrustService.upQuitEtrustEntMoney(map);
            map.put("id", moneyBack.getEntAgent());
            if (moneyBack.getEntAgent() != -1) {
                money1 = moneyBack.getEntMoney() * 0.1;
                map.put("userMoney", moneyBack.getEntMoney() * 0.1 + comUser.getUserMoney());
                msg += "给委托人返回" + money2
                        + "给接单人返回+" + money1;
            } else {
                msg += "给委托人返回" + money2;
            }
            entrustService.upQuitEtrustEntMoney(map);
            Integer id = moneyBack.getEntrustId();
            entrustService.delLeisureEntrustById2(id);
            return msg;
        }
    }
    @GetMapping("handleDeleteById/{id}")
    public String handleDeleteById(@PathVariable Integer id) {
        try {
            entrustService.handleDeleteById(id);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    @PostMapping("verifyAddEntType")
    public String verifyAddEntType(
            @RequestBody ComEntrustType comEntrustType) {
        System.out.println(comEntrustType);
        String EntrustType = comEntrustType.getEntType();
        boolean bool = entrustService.judgeTypeRep(EntrustType);
        String msg = "";
        if (bool) {
            msg = "ok";
        } else {
            msg = "失败--有重复属性";
        }
        return msg;
    }

    // 添加委托
    @PostMapping("addEntType")
    public StringBuffer addEntType(
            @RequestBody ComEntrustType comEntrustType) {
        StringBuffer str = new StringBuffer();
        String EntrustType = comEntrustType.getEntType();
        boolean bool = entrustService.addEntrustType(EntrustType);
        if (bool) {
            str.append("ok");
        } else {
            str.append("no");
        }
        return str;
    }


    @PostMapping("byEentyToEntList/{id}/{pageNum}/{pageSize}")
    public String byEentyToEntList(PageToById pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysEntrust> sysEntrustPageInfo = new PageInfo<>(userService.byEntrustByType(pageTo.getId()));
            List<SysEntrust> replies = sysEntrustPageInfo.getList();
            map.put("data", replies);
            map.put("code", "200");
            map.put("total", sysEntrustPageInfo.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }
}
