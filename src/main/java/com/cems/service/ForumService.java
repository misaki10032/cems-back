package com.cems.service;

import com.cems.pojo.ForumArticle;
import com.cems.pojo.to.FormArticle;

import java.util.List;

/**
 * @InterfaceName ForumService
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
public interface ForumService {

    List<ForumArticle> getArticles();

    List<ForumArticle> getArticleLike(FormArticle form);

    void killArticle(int id, String status);
}
