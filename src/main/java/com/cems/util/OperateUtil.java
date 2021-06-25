package com.cems.util;

import com.cems.pojo.SysAdmin;
import com.cems.pojo.SysAdminSuc;
import com.cems.service.SysAdminService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OperateUtil
 * @Author 陈新予(blank)
 * @Date 2021/6/25
 * @Version 1.0
 */
public class OperateUtil {
    public static void addOperate(HttpSession session, SysAdminService adminService) {
        SysAdmin admin = (SysAdmin) session.getAttribute("LogingAdmin");
        SysAdminSuc sysAdminSuc = adminService.selOneSysSuc(admin.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("adminId", admin.getId());
        map.put("operateNum", sysAdminSuc.getOperateNum() + 1);
        adminService.setSysLoginGraph(map);
    }
}
