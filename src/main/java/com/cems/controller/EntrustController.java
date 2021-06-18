package com.cems.controller;


import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.service.ComEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String getAllEnts(){
        List<ComEntrust> entrusts = entrustService.getEntrusts();
        String s = JSON.toJSONString(entrusts);
        System.out.println(s);
        return s;
    }
}
