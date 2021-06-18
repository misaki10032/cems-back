package com.cems.service.impl;



import com.cems.mapper.AdminMapper;
import com.cems.pojo.SysAdmin;
import com.cems.service.SysAdminService;
import com.cems.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName SysAdminServiceImpl
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
@Service
public class SysAdminServiceImpl implements SysAdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public List<SysAdmin> getAdmins() {
        return adminMapper.selectList(null);
    }
}
