package com.cems.controller.uniApp.user;

import com.cems.pojo.UniUserFriend;
import com.cems.pojo.to.ComUser;
import com.cems.service.ComUserService;
import com.cems.service.FriendService;
import com.cems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName UniUserController
 * @Author 陈新予(blank)
 * @Date 2021/6/27
 * @Version 1.0
 */
@RestController
@RequestMapping("uniApp")
public class UniUserController {
    @Autowired
    ComUserService comUserService;
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @PostMapping("revUserInfo")
    public Map<String, Object> revUserInfo(@RequestBody ComUser user) {
        System.out.println(user);
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            comUserService.revUserInfo(user);
            map.put("code", "200");
            map.put("msg", "修改成功!");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器异常!");
        }
        return map;
    }

    @GetMapping("getThisUser")
    public Map<String, Object> getThisUser(int id) {
        System.out.println(id);
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            ComUser userById = comUserService.getUserById(id);
            map.put("code", "200");
            map.put("userInfo", userById);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器异常!");
        }
        return map;
    }

    @GetMapping("addFriend")
    public Map<String, Object> addFriend(int userId, int friendId) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            boolean contains = friendService.FriendContains(userId, friendId);
            if (!contains) {
                map.put("code", "501");
                map.put("msg", "已经关注该用户了!");
                return map;
            }
            friendService.addFriend(userId, friendId);
            String userPname = comUserService.getUserById(friendId).getUserPname();
            map.put("code", "200");
            map.put("msg", "成功关注[" + userPname + "]!");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器故障!");
        }
        return map;
    }

    @GetMapping("getMyFriend")
    public Map<String, Object> getMyFriend(int id) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            List<UniUserFriend> myFriend = friendService.getMyFriend(id);
            map.put("code", "200");
            map.put("data", myFriend);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器故障!");
        }
        return map;
    }

    @GetMapping("getMyFocus")
    public Map<String, Object> getMyFocus(int id) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        try {
            List<UniUserFriend> myFocus = friendService.getMyFocus(id);
            map.put("code", "200");
            map.put("data", myFocus);
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", "服务器故障!");
        }
        return map;
    }

}
