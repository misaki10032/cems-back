package com.cems.service.impl;



import com.cems.mapper.AdminMapper;
import com.cems.pojo.*;
import com.cems.pojo.to.LevelUpDTO;
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
    public List<SysAdminInfoBig> getAllAdminInfo() {
        return adminMapper.getAllAdminInfo();
    }

    @Override
    public void killAdmin(int id, String status) {
        adminMapper.killAdmin(id, status);
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
    public int insApple(Map<String, Object> map) {
        return adminMapper.insApple(map);
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
    public void changePassword(Map<String, Object> map) {
        adminMapper.changePassword(map);
    }

    @Override
    public int insertSysSuc(int id) {
        return adminMapper.insertSysSuc(id);
    }

    @Override
    public SysAdminSuc selOneSysSuc(int adminId) {
        return adminMapper.selOneSysSuc(adminId);
    }

    @Override
    public int setSysLoginGraph(Map<String, Object> map) {
        return adminMapper.setSysLoginGraph(map);
    }

    @Override
    public void updateAdminInfo(SysAdminInfo sysAdminInfo) {
        adminMapper.updateAdminInfo(sysAdminInfo);
    }

    @Override
    public int delAllUp(String status) {
        return adminMapper.delAllUp(status);
    }

    @Override
    public List<SysAdminInfoBig> selByEmailId(Map<String, Object> map) {
        return adminMapper.selByEmailId(map);
    }

    @Override
    public int forgetPswOk(Map<String, Object> map) {
        return adminMapper.forgetPswOk(map);
    }

    @Override
    public SysAdminInfo getAdminInfo(String adminNum) {
        return adminMapper.getAdminInfo(adminNum);
    }

    @Override
    public SysAdmin selOneSysZC(String acc) {
        return adminMapper.selOneSysZC(acc);
    }

    @Override
    public void addLevelUp(LevelUpDTO level) {
        adminMapper.addLevelUp(level);
    }

    @Override
    public void changePwd(int id, String pwd) {
        adminMapper.changePwd(id, pwd);
    }

    @Override
    public List<SysUpgrade> selAllUp() {
        return adminMapper.selAllUp();
    }

    @Override
    public SysUpgrade getAdminUp(int id) {
        return adminMapper.getAdminUp(id);
    }

    @Override
    public int upAdminOk(Map<String, Object> map) {
        return adminMapper.upAdminOk(map);
    }

    @Override
    public int changeUpStatus(Map<String, Object> map) {
        return adminMapper.changeUpStatus(map);
    }
}
