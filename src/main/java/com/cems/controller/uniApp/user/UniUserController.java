package com.cems.controller.uniApp.user;

import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.LoginUser;
import com.cems.service.ComUserService;
import com.cems.service.UserService;
import com.cems.util.ShiroMd5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("chaPwd")
    public Map<String, Object> chaPwd(@RequestBody LoginUser loginUser) {
        System.out.println(loginUser);
        Map<String, Object> serMap = new ConcurrentHashMap<>();
        Map<String, Object> map = new ConcurrentHashMap<>();

//        Integer id = loginUser.getId();
        String psw1 = ShiroMd5Util.toPwdMd5(loginUser.getUserPhone(), loginUser.getPsw());
        System.err.println("旧密码"+psw1);
        String psw2 = ShiroMd5Util.toPwdMd5(loginUser.getUserPhone(), loginUser.getNewPsw());
        System.err.println("新密码"+psw2);
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
                comUserService.updateUserPsw(loginUser.getId(),psw2);
                map.put("code", 200);
            }else{
                //密码错误
                map.put("code", 400);
            }
        } catch (Exception e) {
            map.put("code", 500);
        }
        return map;

    }

}
