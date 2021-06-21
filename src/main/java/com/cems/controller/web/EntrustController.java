package com.cems.controller.web;


import ch.qos.logback.core.pattern.color.MagentaCompositeConverter;
import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.MoneyBack;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.PageTo;
import com.cems.service.ComEntrustService;
import com.cems.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getAllEnts")
    public String getAllEnts() {
        List<ComEntrust> entrusts = entrustService.getEntrusts();
        String s = JSON.toJSONString(entrusts);
        System.out.println(s);
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
            System.out.println(entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }

        return JSON.toJSONString(map);
    }

    /**
     *
     */
    @GetMapping("updataEntState")
    public String updataEntState(Integer rowid,
                                 String rowstatus) {
        System.out.println(rowid);
        System.out.println(rowstatus);

        try {
            if (rowstatus.equals("已审核")) {
                rowstatus = "未审核";
            } else {
                rowstatus = "已审核";
            }
            entrustService.updataEntState(rowid, rowstatus);
            System.out.println(rowstatus);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * 获得所有的类型名称 以及对应的数量
     *
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
            System.out.println(entList.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/MoneyBack")
    public String MoneyBack(@RequestBody MoneyBack moneyBack) {
        Double money1 = null;
        Double money2 = null;
        System.err.println("entPlan___>>>>+" + moneyBack.getEntPlan());
        String msg = "";
        if (moneyBack.getEntPlan().equals("已完成")) {
            //订单已完成 不可推荐
            msg += "ok";
            System.err.println("msgmsgmsgmsg==??" + msg);
            return msg;
        } else {

            System.out.println("委托的价格是 entMoney" + moneyBack.getEntMoney());
            //给委托人反钱
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
            System.err.println(map);
            int count = entrustService.upQuitEtrustEntMoney(map);
            //给给代理人加钱一定的
            //查询委托人 当前的总余额
            ComUser comUser1 = userService.selOneUser(moneyBack.getEntAgent());
            map.put("id", moneyBack.getEntAgent());
            if (moneyBack.getEntAgent() != -1) {

                money1 = moneyBack.getEntMoney() * 0.1;

                map.put("userMoney", moneyBack.getEntMoney() * 0.1 + comUser.getUserMoney());
            }
            int count1 = entrustService.upQuitEtrustEntMoney(map);
            if (moneyBack.getEntAgent() != -1) {
                msg += "给委托人返回" + money2
                        + "给接单人返回+" + money1;
            } else {
                msg += "给委托人返回" + money2;
            }
            //删除委托
            Integer id = moneyBack.getEntrustId();
            entrustService.delLeisureEntrustById2(id);
            System.err.println("=====>>>>>?????" + msg);
            return msg;


        }


    }


    @GetMapping("handleDeleteById/{id}")
    public String handleDeleteById(@PathVariable Integer id) {
        String msg = "";
        try {
            System.err.println("--------------"+msg);
            System.err.println("--------------"+id);
            entrustService.handleDeleteById(id);
            msg="ok";
            System.err.println("++++++++++"+msg);
            System.err.println("--------------"+id);
            return msg;
        } finally {
            msg = "no";
            System.err.println("sssssssssssssssss+"+msg);
            System.err.println("--------------"+id);
            return msg;
        }
    }
}
