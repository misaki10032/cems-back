package com.cems.controller.uniApp.user;

import com.cems.pojo.ForumArticle;
import com.cems.pojo.Message;
import com.cems.pojo.UniUserFriend;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginUser;
import com.cems.pojo.uni.UniUpUserSole;
import com.cems.service.ComUserService;
import com.cems.service.FriendService;
import com.cems.service.UserService;
import com.cems.util.DateUtil;
import com.cems.util.ShiroMd5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @PostMapping("chaPwd")
    public Map<String, Object> chaPwd(@RequestBody LoginUser loginUser) {
        System.out.println(loginUser);
        Map<String, Object> serMap = new ConcurrentHashMap<>();
        Map<String, Object> map = new ConcurrentHashMap<>();
        String psw1 = ShiroMd5Util.toPwdMd5(loginUser.getUserPhone(), loginUser.getPsw());
        System.err.println("旧密码" + psw1);
        String psw2 = ShiroMd5Util.toPwdMd5(loginUser.getUserPhone(), loginUser.getNewPsw());
        System.err.println("新密码" + psw2);
        //账号
        serMap.put("id", loginUser.getId());
        //旧密码
        serMap.put("psw", psw1);
        //新密码
        serMap.put("pass", psw2);
        System.out.println(serMap);
        try {
            ComUser comUser = comUserService.getUserById(loginUser.getId());
            System.err.println(comUser);
            if (psw1.equals(comUser.getUserPwd())) {
                comUserService.updateUserPsw(loginUser.getId(), psw2);
                map.put("code", 200);
            } else {
                //密码错误
                map.put("code", 400);
            }
        } catch (Exception e) {
            map.put("code", 500);
        }
        return map;

    }

    @PostMapping("upUserRole")
    public Map<String, Object> upUserRole(@RequestBody UniUpUserSole UniUpUserSole) {
        System.out.println(UniUpUserSole);
        Map<String, Object> map = new ConcurrentHashMap<>();
        Integer money = UniUpUserSole.getMoney();
        Integer upMoney = UniUpUserSole.getUpMoney();
        if (money - upMoney < 0) {
            map.put("code", 500);
            return map;
        }
        try {
            comUserService.updateUserRole(UniUpUserSole.getId(), "complete", money - upMoney);
            map.put("code", 200);
        } catch (Exception e) {
            map.put("code", 500);
        }
        System.out.println(map);
        return map;
    }

    @PostMapping("addUserMoney")
    public Map<String, Object> addUserMoney(@RequestBody UniUpUserSole UniUpUserSole) {
        System.out.println(UniUpUserSole);
        Map<String, Object> map = new ConcurrentHashMap<>();
        ComUser comUser = comUserService.getUserById(UniUpUserSole.getId());
        Integer userMoney = comUser.getUserMoney();
        Integer upMoney = UniUpUserSole.getUpMoney() + userMoney;
        try {
            comUserService.addUserMoney(UniUpUserSole.getId(), upMoney);
            comUser.setUserMoney(upMoney);
            map.put("code", 200);
            map.put("loginUser", comUser);
            map.put("msg", "充值成功!");
        } catch (Exception e) {
            map.put("code", 500);
            map.put("loginUser", comUser);
            map.put("msg", "请稍后重试!");
        }
        System.err.println(map);
        return map;
    }


    @GetMapping("selArticleByUId")
    public Map<String, Object> selArticleByUId(String pageIndex, String pageSize, String id) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            List<ForumArticle> forumArticles = userService.selArticleByUId(Integer.parseInt(id));
            for (ForumArticle forum:forumArticles) {
                forum.setDel(true);
            }
            System.out.println(forumArticles);
            PageInfo<ForumArticle> forumList = new PageInfo<>(forumArticles);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", forumList.getList());
            map.put("total", forumList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }
    @GetMapping("delArticle")
    public String delArticle( String id) {
        try {
            userService.delArticeById(Integer.parseInt(id));
            return "";
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

    @GetMapping("selAllArticle")
    public Map<String, Object> selAllArticle(String pageIndex, String pageSize, String id) {
        try {
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            List<ForumArticle> forumArticles = userService.selAllArtice();
            for (ForumArticle forum:forumArticles) {
                forum.setDel(true);
            }
            System.out.println(forumArticles);
            PageInfo<ForumArticle> forumList = new PageInfo<>(forumArticles);
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("data", forumList.getList());
            map.put("total", forumList.getTotal());
            return map;
        } catch (Exception e) {
            System.err.println("查找失败!!!");
            return null;
        }
    }

}
