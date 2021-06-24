package com.cems.controller.uniApp.Info;

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
 * @Date 2021/6/24
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class Uni_EntrustController {
    @Autowired
    ComEntrustService entrustService;

    @GetMapping("getEnts")
    public String getEnts() {
        List<ComEntrust> entrusts = entrustService.getEntrusts();
        return JSON.toJSONString(entrusts);
    }
}
