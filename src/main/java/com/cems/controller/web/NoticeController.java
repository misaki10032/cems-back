package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.SysAdminInfoBig;
import com.cems.pojo.SysNotice;
import com.cems.pojo.to.PageTo;
import com.cems.service.SysNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("web")
public class NoticeController {

    @Autowired
    SysNoticeService noticeService;
    @PostMapping("getAllNotice/{pageNum}/{pageSize}")
    public String getAllNotice(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println("pageTo===="+pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<SysNotice> sysAdminInfoBigPageInfo = new PageInfo<>(noticeService.selAllNotice());
            List<SysNotice> sysAdminInfoBigs = sysAdminInfoBigPageInfo.getList();
            map.put("data", sysAdminInfoBigs);
            map.put("code", "200");
            map.put("total", sysAdminInfoBigPageInfo.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("delOne/{id}")
    public String delOne(@PathVariable Integer id) {
        noticeService.delOneNotice(id);
        return "ok";
    }
}
