package com.cems.config;




import com.cems.pojo.SysAdmin;
import com.cems.service.SysAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/16 15:22
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    SysAdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        String realmName = getName();
        List<SysAdmin> admins = adminService.getAdmins();
        for (SysAdmin admin : admins) {
            if(admin.getAdminNum().equals(username)){
                if (!admin.getAdminStatus().equals("正常")) {
                    throw new LockedAccountException();
                }
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("LogingAdmin",admin);
                ByteSource salt = ByteSource.Util.bytes(admin.getAdminNum());//md5盐值
                return new SimpleAuthenticationInfo(admin,admin.getAdminPwd(),salt,realmName);
            }
        }
        return null;
    }

}
