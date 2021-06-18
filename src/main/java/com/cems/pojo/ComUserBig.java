package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName ComUserBig
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComUserBig {
    private int id;
    private String userPhone;
    private String userPwd;
    private String userRole;
    private String status;
    private String userEmail;
    private String userHouse;
    private String userBirth;
    private String userPwdProtect;
    private String userDec;
    private String userName;
    private String userPname;
    private String userSex;
    private int userMoney;
    private String userPicture;
}
