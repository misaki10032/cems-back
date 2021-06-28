package com.cems.service;

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


}
