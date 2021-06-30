package com.cems.service.impl;

import com.cems.mapper.FriendMapper;
import com.cems.mapper.UserMapper;
import com.cems.pojo.UniUserFriend;
import com.cems.pojo.uni.UniMyFriend;
import com.cems.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FriendServiceImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void addFriend(int userId, int friendId) {
        friendMapper.addFriend(userId, friendId);
    }

    @Override
    public UniMyFriend getMyFocus(int id) {
        return userMapper.getMyFocus(id);
    }

    @Override
    public UniMyFriend getMyFriend(int id) {
        return userMapper.getMyFriend(id);
    }

    @Override
    public boolean FriendContains(int userId, int friendId) {
        List<UniUserFriend> msg = friendMapper.getMsg(userId, friendId);
        if (msg.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int countUserFans(int id) {
        return friendMapper.countFans(id);
    }

    @Override
    public int countUserFocus(int id) {
        return friendMapper.countFocus(id);
    }
}
