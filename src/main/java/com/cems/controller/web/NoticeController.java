package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfoBig;
import com.cems.pojo.SysNotice;
import com.cems.pojo.to.Appeal;
import com.cems.pojo.to.Notice;
import com.cems.pojo.to.PageTo;
import com.cems.service.SysNoticeService;
import com.cems.util.DateUtil;
import com.cems.util.ShiroMd5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<SysNotice> sysNotices = noticeService.selAllNotice();
            PageInfo<SysNotice> sysAdminInfoBigPageInfo = new PageInfo<>(sysNotices);
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

    @PostMapping("noticeOk")
    public String register(@RequestBody Notice notice){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(notice.getTime());
            Date date1 = simpleDateFormat.parse(notice.getTime1());
            Map<String,Object> map = new HashMap<>();
            map.put("content",notice.getText());
            map.put("begin",date);
            map.put("end",date1);
            map.put("adminId",notice.getAdminId());
            noticeService.insNotice(map);
        } catch (ParseException e) {
            return "200";
        }
        return "200";
    }
}
