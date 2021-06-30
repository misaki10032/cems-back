package com.cems.service.impl;

import com.cems.mapper.UserMapper;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.Message;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.SysEntrust;
import com.cems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/19  20:46
 */

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ComUser selOneUser(int id) {
        return userMapper.selOneUser(id);
    }

    @Override
    public List<SysEntrust> byEntrustByType(Integer typeID) {
        return userMapper.byEntrustByType(typeID);
    }

    @Override
    public List<Message> selAllMessageById(int userId) {
        return userMapper.selAllMessageById(userId);
    }

    @Override
    public int delMessage(int id) {
        return userMapper.delMessage(id);
    }

    @Override
    public int delMessageByUid(int id) {
        return userMapper.delMessageByUid(id);
    }

    @Override
    public List<ForumArticle> selArticleByUId(int userId) {
        return userMapper.selArticleByUId(userId);
    }

    @Override
    public List<ForumArticle> selDelArticleByUId(int artId) {
        return userMapper.selDelArticleByUId(artId);
    }

    @Override
    public ForumArticle selArticleById(int id) {
        return userMapper.selArticleById(id);
    }

    @Override
    public int delArticeById(int id) {
        return userMapper.delArticeById(id);
    }
}
