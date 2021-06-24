package com.cems.service;


import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfo;
import com.cems.pojo.SysShenSu;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName SysAdminService
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
public interface SysAdminService {

    List<SysAdmin> getAdmins();

    SysAdmin getAdminNum(String num);

    /**
     * 管理员申诉账号密码验证
     */
    SysAdmin gljudgeAP(Map<String, Object> map);

    /**
     * 管理员注册
     */
    int registerSys(Map<String, Object> map);

    /**
     * 添加邮箱
     */
    int insertEmail(Map<String, Object> map);

    /**
     * 管理员注册差重复
     */
    List<SysAdmin> selOneSysByAcc(String acc);

    /**
     * 管理员注册差重复(手机号,邮箱)
     */
    List<SysAdminInfo> selOneSysByEP(Map<String, Object> map);

    List<SysAdminInfo> selOneSysByEP2(Map<String, Object> map);

    /**
     * 管理员申诉查询
     */
    List<SysShenSu> selAllAppeal();

    /**
     * 管理员申诉
     */
    int insApple(Map<String, Object> map);

    /**
     * 管理员申诉成功
     */
    int shensuOK(String adminNum);

    /**
     * 管理员申诉成功,删除申诉
     */
    int delSS(Integer id);

    /**
     * 根据账号查询管理员
     */
    SysAdmin selOneSysZC(String acc);

    /*
    根据账号查询管理员详细信息
     */
    SysAdminInfo getAdminInfo(String adminNum);

    /*
      更改管理员个人数据
    */
    void updateAdminInfo(SysAdminInfo sysAdminInfo);
}
