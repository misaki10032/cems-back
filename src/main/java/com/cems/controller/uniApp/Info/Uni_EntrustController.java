package com.cems.controller.uniApp.Info;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.to.PageTo;
import com.cems.service.ComEntrustService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EntrustController
 * @Author 陈新予(blank)
 * @Date 2021/6/24
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class Uni_EntrustController {
    @Autowired
    ComEntrustService entrustService;

    @GetMapping("getEnts")
    public Map<String, Object> getEnts(String pageIndex, String pageSize) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<ComEntrust> entList = new PageInfo<>(entrustService.getEntrusts());
            Map<String, Object> map = new HashMap<>();
            map.put("data", entList.getList());
            map.put("total", entList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }
}