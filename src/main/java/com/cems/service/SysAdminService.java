package com.cems.service;


import com.cems.pojo.SysAdmin;

import java.util.List;

/**
 * @InterfaceName SysAdminService
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
public interface SysAdminService {

    List<SysAdmin> getAdmins();

    SysAdmin getAdminNum(String num);

}
