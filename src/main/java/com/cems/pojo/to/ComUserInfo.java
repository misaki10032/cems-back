package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName ComUserInfo
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComUserInfo {
    private int id;
    private int userId;
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
