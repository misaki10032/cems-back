package com.cems.service;

import com.cems.pojo.ForumArticle;
import com.cems.pojo.Message;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.SysEntrust;

import java.util.List;

/*
 *
 *@auth  NieWenZhao
 *@date 2021/6/19  20:44
 */
public interface UserService {

    ComUser selOneUser(int id);

    List<SysEntrust> byEntrustByType(Integer typeID);

    List<Message> selAllMessageById(int userId);
    //删除
    int delMessage(int id);

    int delMessageByUid(int id);

    List<ForumArticle> selArticleByUId(int userId);

    List<ForumArticle> selDelArticleByUId(int artId);

    ForumArticle  selArticleById(int id);

    int delArticeById(int id);

    List<ForumArticle> selAllArtice();

}
