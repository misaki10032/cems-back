package com.cems.service.impl;



import com.cems.mapper.AdminMapper;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfo;
import com.cems.pojo.SysShenSu;
import com.cems.service.SysAdminService;
import com.cems.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    @Override
    public SysAdmin getAdminNum(String num) {
        return adminMapper.getAdminNum(num);
    }

    @Override
    public SysAdmin gljudgeAP(Map<String, Object> map) {
        return adminMapper.gljudgeAP(map);
    }

    @Override
    public int registerSys(Map<String, Object> map) {
        return adminMapper.registerSys(map);
    }

    @Override
    public int insertEmail(Map<String, Object> map) {
        return adminMapper.insertEmail(map);
    }

    @Override
    public List<SysAdmin> selOneSysByAcc(String acc) {
        return adminMapper.selOneSysByAcc(acc);
    }

    @Override
    public List<SysAdminInfo> selOneSysByEP(Map<String, Object> map) {
        return adminMapper.selOneSysByEP(map);
    }

    @Override
    public List<SysAdminInfo> selOneSysByEP2(Map<String, Object> map) {
        return adminMapper.selOneSysByEP2(map);
    }

    @Override
    public List<SysShenSu> selAllAppeal() {
        return adminMapper.selAllAppeal();
    }

    @Override
    public int shensuOK(String adminNum) {
        return adminMapper.shensuOK(adminNum);
    }

    @Override
    public int delSS(Integer id) {
        return adminMapper.delSS(id);
    }

    @Override
    public SysAdmin selOneSysZC(String acc) {
        return adminMapper.selOneSysZC(acc);
    }
}
