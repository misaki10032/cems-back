package com.cems.service.impl;



import com.cems.mapper.UserMapper;
import com.cems.pojo.ComUserBig;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.ComUserInfo;
import com.cems.pojo.to.FormInline;
import com.cems.service.ComUserService;
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
    public void KillUser(int id,String status) {
        userMapper.killUser(id,status);
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

}
