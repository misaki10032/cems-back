package com.cems.controller.uniApp.Info;

import com.cems.pojo.uni.UniArticle;
import com.cems.pojo.uni.UniComment;
import com.cems.pojo.uni.UniReply;
import com.cems.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Uni_ArticleController
 * @Author 陈新予(blank)
 * @Date 2021/7/2
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class Uni_ArticleController {
    @Autowired
    ForumService forumService;
    @GetMapping("toArticleInfo")
    public Map<String,Object> toArticleInfo(int id){
        Map<String,Object> map = new ConcurrentHashMap<>();
        try{
            UniArticle articleInfo = forumService.getArticleInfo(id);
            map.put("code",200);
            map.put("msg","查询成功!");
            map.put("article",articleInfo);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","帖子找不到了!");
        }
        return map;
    }
    @GetMapping("addComment")
    public Map<String,Object> addComment(UniComment comment){
        Map<String,Object> map = new ConcurrentHashMap<>();
        try {
            int cid = forumService.addComment(comment);
            map.put("code",200);
            map.put("msg","评论成功!!");
            map.put("评论号",cid);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","评论失败!!");
        }
        return map;
    }
    @GetMapping("addReply")
    public Map<String,Object> addReply(UniReply reply){
        Map<String,Object> map = new ConcurrentHashMap<>();
        try {
            int cid = forumService.addReply(reply);
            map.put("code",200);
            map.put("msg","评论成功!!");
            map.put("评论号",cid);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","评论失败!!");
        }
        return map;
    }

}
