package com.cems.service;


import com.cems.pojo.SysAdmin;

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
}
