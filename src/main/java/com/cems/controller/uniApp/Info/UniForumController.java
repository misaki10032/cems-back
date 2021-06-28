package com.cems.controller.uniApp.Info;

import com.cems.pojo.uni.UniForum;
import com.cems.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/28  17:44
 */
@RestController
@RequestMapping("uniApp")
public class UniForumController {
    @Autowired
    ForumService forumService;

    @PostMapping("addForum")
    public Map<String, Object> addForum(@RequestBody UniForum uniForum) {
        System.out.println(uniForum);
        Map<String, Object> retMap = new ConcurrentHashMap<>();
        try {
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("userId", uniForum.getId());
            map.put("foTitle", uniForum.getTitle());
            map.put("foDate", uniForum.getData());
            forumService.addForum(map);
            retMap.put("code", 200);
            retMap.put("msg", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("code", 400);
            retMap.put("msg", "网络异常");
        }
        return retMap;
    }
}
