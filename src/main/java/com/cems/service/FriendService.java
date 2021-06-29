package com.cems.service;

import com.cems.pojo.UniUserFriend;

import java.util.List;

/**
 * @InterfaceName FriendService
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
public interface FriendService {

    void addFriend(int userId, int friendId);

    List<UniUserFriend> getMyFocus(int id);

    List<UniUserFriend> getMyFriend(int id);

    boolean FriendContains(int userId, int friendId);

}
