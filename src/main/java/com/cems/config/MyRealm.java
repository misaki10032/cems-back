package com.cems.config;




import com.cems.pojo.SysAdmin;
import com.cems.pojo.to.ComUser;
import com.cems.service.ComUserService;
import com.cems.service.SysAdminService;
import com.cems.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/4/16 15:22
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    SysAdminService adminService;
    @Autowired
    ComUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        String realmName = getName();
        Session session = SecurityUtils.getSubject().getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin) {
            SysAdmin admin;
            try {
                admin = adminService.getAdminNum(username);
            } catch (Exception e) {
                throw new UnknownAccountException();
            }
            if (admin == null) {
                throw new UnknownAccountException();
            } else {
                if (!admin.getAdminStatus().equals("正常")) {
                    throw new LockedAccountException();
                } else {
                    session.setAttribute("LogingAdmin", admin);
                    ByteSource salt = ByteSource.Util.bytes(admin.getAdminNum());//md5盐值
                    return new SimpleAuthenticationInfo(admin, admin.getAdminPwd(), salt, realmName);
                }
            }
        } else {
            ComUser user;
            try {
                user = userService.getUserNum(username);
            } catch (Exception e) {
                throw new UnknownAccountException();
            }
            if (!user.getStatus().equals("正常")) {
                throw new LockedAccountException();
            } else {
                session.setAttribute("loginUser", user);
                ByteSource salt = ByteSource.Util.bytes(user.getUserPhone());//md5盐值
                return new SimpleAuthenticationInfo(user, user.getUserPwd(), salt, realmName);
            }

        }
    }

}
