package com.cems.service;

import com.cems.pojo.CommentReply;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.ForumComment;
import com.cems.pojo.to.FormArticle;
import com.cems.pojo.to.FormInline;
import com.cems.pojo.uni.UniArticle;
import com.cems.pojo.uni.UniComment;
import com.cems.pojo.uni.UniReply;

import java.util.List;
import java.util.Map;

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

    List<ForumComment> getComments();

    List<ForumComment> getCommentLike(FormInline form);

    List<CommentReply> getReplyByCommId(int id);

    List<CommentReply> getReplyLike(FormInline form);

    void addForum(Map<String, Object> map);

    UniArticle getArticleInfo(int id);

    int addComment(UniComment comment);

    int addReply(UniReply reply);
}
