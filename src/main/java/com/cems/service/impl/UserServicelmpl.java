package com.cems.service.impl;

import com.cems.mapper.UserMapper;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.SysEntrust;
import com.cems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/19  20:46
 */

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ComUser selOneUser(int id) {
        return userMapper.selOneUser(id);
    }

    @Override
    public List<SysEntrust> byEntrustByType(Integer typeID) {
        return userMapper.byEntrustByType(typeID);
    }
}
