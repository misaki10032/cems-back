package com.cems.service;


import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminInfo;

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
     /**管理员申诉账号密码验证*/
     SysAdmin gljudgeAP(Map<String,Object> map);
     /**管理员注册*/
     int registerSys(Map<String,Object> map);
     /**添加邮箱*/
     int insertEmail(Map<String,Object> map);
     /**管理员注册差重复*/
     List<SysAdmin> selOneSysByAcc(String acc);
     /**管理员注册差重复(手机号,邮箱)*/
     List<SysAdminInfo> selOneSysByEP(Map<String,Object> map);
     List<SysAdminInfo> selOneSysByEP2(Map<String,Object> map);
}
