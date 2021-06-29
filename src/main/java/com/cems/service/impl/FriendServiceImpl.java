package com.cems.service.impl;

import com.cems.mapper.FriendMapper;
import com.cems.pojo.UniUserFriend;
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

    @Override
    public void addFriend(int userId, int friendId) {
        friendMapper.addFriend(userId, friendId);
    }

    @Override
    public List<UniUserFriend> getMyFocus(int id) {
        return friendMapper.getMyFocus(id);
    }

    @Override
    public List<UniUserFriend> getMyFriend(int id) {
        return friendMapper.getMyFriend(id);
    }

    @Override
    public boolean FriendContains(int userId, int friendId) {
        List<UniUserFriend> msg = friendMapper.getMsg(userId, friendId);
        if (msg.size() == 0) {
            return true;
        }
        return false;
    }
}
