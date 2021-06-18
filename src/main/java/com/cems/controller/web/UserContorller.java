package com.cems.controller.web;


import com.alibaba.fastjson.JSON;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.FormInline;
import com.cems.pojo.to.PageTo;
import com.cems.service.ComUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserContorller
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@RestController
@RequestMapping("web")
public class UserContorller {
    @Autowired
    ComUserService userService;

    @GetMapping("getAllUsers")
    public String getUsers() {
        List<ComUser> users = userService.getUsers();
        String s = JSON.toJSONString(users);
        System.out.println(s);
        return s;
    }

    @GetMapping("killuser")
    public String KillUser(Integer rowid, String rowstatus) {
        try {
            if (rowstatus.equals("正常")) {
                rowstatus = "正常";
            } else {
                rowstatus = "封禁";
            }
            userService.KillUser(rowid, rowstatus);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
    }

    @PostMapping("/getUserlimit/{pageNum}/{pageSize}")
    public String getUserLimit(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try{
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<ComUser> userlimit = new PageInfo<>(userService.getUsers());
            List<ComUser> userlimitList = userlimit.getList();
            map.put("data",userlimitList);
            map.put("code","200");
            map.put("total",userlimit.getTotal());
            System.out.println(userlimit.getTotal());
        }catch (Exception e){
            map.put("code","500");
        }
        return JSON.toJSONString(map);
    }
    @PostMapping("/getUserLike")
    public String chaxun(@RequestBody FormInline form) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(form);
        try{
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
            PageInfo<ComUser> userlimit = new PageInfo<>(userService.getUserLike(form));
            List<ComUser> userlimitList = userlimit.getList();
            map.put("data",userlimitList);
            map.put("code","200");
            map.put("total",userlimit.getTotal());
            System.out.println(userlimit.getTotal());
        }catch (Exception e){
            map.put("code","500");
        }
        return JSON.toJSONString(map);
    }
}
