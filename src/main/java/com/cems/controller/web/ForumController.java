package com.cems.controller.web;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.ComEntrust;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.FormArticle;
import com.cems.pojo.to.FormInline;
import com.cems.pojo.to.PageTo;
import com.cems.service.ForumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ForumController
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@RestController
@RequestMapping("web")
public class ForumController {
    @Autowired
    ForumService forumService;

    @PostMapping("/getArtlimit/{pageNum}/{pageSize}")
    public String getArticle(PageTo pageTo) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(pageTo);
        try {
            PageHelper.startPage(pageTo.getPageNum(), pageTo.getPageSize());
            PageInfo<ForumArticle> articlePageInfo = new PageInfo<>(forumService.getArticles());
            List<ForumArticle> articlesLimitList = articlePageInfo.getList();
            map.put("data", articlesLimitList);
            map.put("code", "200");
            map.put("total", articlePageInfo.getTotal());
            System.out.println(articlePageInfo.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @PostMapping("/getArtLike")
    public String getArtInfo(@RequestBody FormArticle form) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(form);
        try {
            PageHelper.startPage(form.getPageNum(), form.getPageSize());
            PageInfo<ForumArticle> articlePageInfo = new PageInfo<>(forumService.getArticleLike(form));
            List<ForumArticle> articles = articlePageInfo.getList();
            map.put("data", articles);
            map.put("code", "200");
            map.put("total", articlePageInfo.getTotal());
            System.out.println(articlePageInfo.getTotal());
        } catch (Exception e) {
            map.put("code", "500");
        }
        return JSON.toJSONString(map);
    }

    @GetMapping("killArticle")
    public String KillUser(Integer rowid, String rowstatus) {
        try {
            if (rowstatus.equals("已审核")) {
                rowstatus = "已审核";
            } else {
                rowstatus = "未审核";
            }
            forumService.killArticle(rowid, rowstatus);
            return rowstatus;
        } catch (Exception e) {
            return "0";
        }
    }

}
