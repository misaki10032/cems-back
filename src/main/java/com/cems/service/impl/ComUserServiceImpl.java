package com.cems.service.impl;


import com.cems.mapper.UserMapper;
import com.cems.pojo.ComUserBig;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.ComUserInfo;
import com.cems.pojo.to.FormInline;
import com.cems.service.ComUserService;
import com.cems.util.ShiroMd5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ComUserServiceImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Service
public class ComUserServiceImpl implements ComUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<ComUser> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public List<ComUserInfo> getUsersInfo() {
        return userMapper.getUsersInfo();
    }

    @Override
    public List<ComUserBig> getUserBigs() {
        return null;
    }

    @Override
    public void KillUser(int id, String status) {
        userMapper.killUser(id, status);
    }

    @Override
    public List<ComUser> getUserLike(FormInline form) {
        if (form.getUser().equals("") || form.getUser() == null) {
            return userMapper.getUserLike("%", form.getRegion());
        }
        return userMapper.getUserLike("%" + form.getUser() + "%", form.getRegion());
    }

    @Override
    public ComUser getUserNum(String num) {
        ComUser userNum = userMapper.getUserNum(num);
        ComUser userInfo = userMapper.selOneUser(userNum.getId());
        ComUser user = new ComUser();
        user.setId(userNum.getId())
                .setUserPhone(userNum.getUserPhone())
                .setUserPwd(userNum.getUserPwd())
                .setUserRole(userNum.getUserRole())
                .setStatus(userNum.getStatus())
                .setUserId(userInfo.getUserId())
                .setUserPname(userInfo.getUserPname())
                .setUserName(userInfo.getUserName())
                .setUserSex(userInfo.getUserSex())
                .setUserBirth(userInfo.getUserBirth())
                .setUserEmail(userInfo.getUserEmail())
                .setUserHouse(userInfo.getUserHouse())
                .setUserPwdProtect(userInfo.getUserPwdProtect())
                .setUserDec(userInfo.getUserDec())
                .setUserMoney(userInfo.getUserMoney());
        return user;
    }

    @Override
    public ComUser judgeAP(Map<String, Object> map) {
        return userMapper.judgeAP(map);
    }

    @Override
    public void userResiger(ComUser user) {
        user.setUserPwd(ShiroMd5Util.toPwdMd5(user.getUserPhone(), user.getUserPwd()));
        userMapper.userResiger(user);
        ComUser userNum = userMapper.getUserNum(user.getUserPhone());
        user.setUserId(userNum.getId());
        userMapper.addUserInfo(user);
    }

    @Override
    public ComUser getUserEmil(String emil, String phone) {
        ComUser userNum = userMapper.getUserNum(phone);
        ComUser userEmil = userMapper.getUserEmil(emil, String.valueOf(userNum.getId()));
        if (userEmil == null) {
            return null;
        }
        return userEmil;
    }

    @Override
    public void updateUserPwd(int id, String pwd) {
        ComUser comUser = userMapper.getUserById(id);
        System.err.println(id + "=-------------------------=" + pwd);
        //将新密码加密存储
        System.err.println("========================" + comUser);
        String newPwd = ShiroMd5Util.toPwdMd5(comUser.getUserPhone(), pwd);
        System.err.println(newPwd);
        userMapper.updateUserPwd(id, newPwd);
    }

    @Override
    public int revUserInfo(ComUser user) {
        return userMapper.revUserInfo(user);
    }


//    @Override
//    public void updateUserMoney(Integer id, Integer upMoney) {
//        userMapper.updateUserPwd(id, String.valueOf(upMoney));
//    }
//
//    @Override
//    public boolean updateUserRole(Integer id, String userRole) {
//        return userMapper.updateUserRole(id,userRole);
//    }

//    @Override
//    public boolean updateUserRole(Map<String, Object> serMap) {
//        return userMapper.updateUserRole(serMap);
//
//    }

    @Override
    public void updateUserRole(Integer id, String userRole, Integer upMoney) {
        userMapper.updateUserRole(id, userRole);
        String money  = String.valueOf(upMoney);
        userMapper.updateUserMoney(id, money);

    }

    @Override
    public void updateUserPsw(int id, String pwd) {
        userMapper.updateUserPwd(id, pwd);
    }

    @Override
    public ComUser getUserById(int id) {
        return userMapper.getUserById(id);
    }

}
