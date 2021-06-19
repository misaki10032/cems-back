package com.cems.controller.web;


import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ComEntrustType;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.PageTo;
import com.cems.service.ComEntrustService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
}
