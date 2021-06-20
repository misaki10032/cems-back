package com.cems.service.impl;

import com.cems.mapper.ArticleMapper;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.to.FormArticle;
import com.cems.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ForumServeImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Service
public class ForumServeImpl implements ForumService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<ForumArticle> getArticles() {
        return articleMapper.getArticles();
    }

    @Override
    public List<ForumArticle> getArticleLike(FormArticle form) {
        if (form.getTitle() == null || form.getTitle().equals("")) {
            System.out.println("查找:标题->%," + form.getStatus());
            return articleMapper.getArticleLike("%", form.getStatus());
        }
        System.out.println("查找:标题->%" + form.getTitle() + "%," + form.getStatus());
        return articleMapper.getArticleLike("%" + form.getTitle() + "%", form.getStatus());
    }

    @Override
    public void killArticle(int id, String status) {
        articleMapper.killArticle(id, status);
    }
}
