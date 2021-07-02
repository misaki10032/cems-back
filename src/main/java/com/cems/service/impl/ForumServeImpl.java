package com.cems.service.impl;

import com.cems.mapper.ArticleMapper;
import com.cems.mapper.CommentMapper;
import com.cems.mapper.ReplyMapper;
import com.cems.pojo.CommentReply;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.ForumComment;
import com.cems.pojo.to.FormArticle;
import com.cems.pojo.to.FormInline;
import com.cems.pojo.uni.UniArticle;
import com.cems.pojo.uni.UniComment;
import com.cems.pojo.uni.UniReply;
import com.cems.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<ForumArticle> getArticles() {
        return articleMapper.getArticles();
    }

    @Override
    public List<ForumArticle> getArticleLike(FormArticle form) {
        if (form.getTitle() == null || form.getTitle().equals("")) {
            return articleMapper.getArticleLike("%", form.getStatus());
        }
        return articleMapper.getArticleLike("%" + form.getTitle() + "%", form.getStatus());
    }

    @Override
    public void killArticle(int id, String status) {
        articleMapper.killArticle(id, status);
    }

    @Override
    public List<ForumComment> getComments() {
        return commentMapper.getComments();
    }

    @Override
    public List<ForumComment> getCommentLike(FormInline form) {
        if (form.getUser() == null || form.getUser().equals("")) {
            return commentMapper.getCommentLike(form.getUser());
        }
        return commentMapper.getCommentLike("%" + form.getUser() + "%");
    }

    @Override
    public List<CommentReply> getReplyByCommId(int id) {
        return replyMapper.getReplyByCommId(id);
    }

    @Override
    public void addForum(Map<String, Object> map) {
        articleMapper.addForum(map);
    }

    @Override
    public UniArticle getArticleInfo(int id) {
        return articleMapper.getArticleInfo(id);
    }

    @Override
    public int addComment(UniComment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int addReply(UniReply reply) {
        return replyMapper.addReply(reply);
    }

    @Override
    public List<CommentReply> getReplyLike(FormInline form) {
        if (form.getUser() == null || form.getUser().equals("")) {
            return replyMapper.getReplyLike(Integer.parseInt(form.getUser()), "%");
        }
        return replyMapper.getReplyLike(Integer.parseInt(form.getUser()), "%" + form.getRegion() + "%");
    }
}
