package com.cems.service;


import com.cems.pojo.ComUserBig;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.ComUserInfo;
import com.cems.pojo.to.FormInline;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ComUserService
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
public interface ComUserService {

    List<ComUser> getUsers();

    List<ComUserInfo> getUsersInfo();

    List<ComUserBig> getUserBigs();

    void KillUser(int id, String status);

    List<ComUser> getUserLike(FormInline form);

    ComUser getUserNum(String num);

    ComUser judgeAP(Map<String, Object> map);

    void userResiger(ComUser user);

    ComUser getUserEmil(String emil, String phone);

    void updateUserPwd(int id, String pwd);

    int revUserInfo(ComUser user);

    ComUser getUserById(int id);


    void updateUserPsw(int id,String pwd);

    void updateUserRole(Integer id, String userRole, Integer upMoney);

    void addUserMoney(Integer id, Integer upMoney);

    void updataUserMoney(Integer id, Integer upMoney);

    LinkedList<ComUser> getUserAtt(int id);
}
