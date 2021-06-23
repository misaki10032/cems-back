package com.cems.service.impl;

import com.cems.mapper.ArticleMapper;
import com.cems.mapper.CommentMapper;
import com.cems.mapper.ReplyMapper;
import com.cems.pojo.CommentReply;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.ForumComment;
import com.cems.pojo.to.FormArticle;
import com.cems.pojo.to.FormInline;
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
    public List<CommentReply> getReplyLike(FormInline form) {
        if (form.getUser() == null || form.getUser().equals("")) {
            return replyMapper.getReplyLike(Integer.parseInt(form.getUser()), "%");
        }
        return replyMapper.getReplyLike(Integer.parseInt(form.getUser()), "%" + form.getRegion() + "%");
    }
}
