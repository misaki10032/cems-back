package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ComUser
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComUser {
    private int id;
    private String userPhone;
    private String userPwd;
    private String userRole;
    private String status;

    private Integer userId;
    private String  userPname;
    private String userName;
    private String userSex;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date userBirth;
    private String userEmail;
    private String userHouse;
    private String userPwdProtect;//密保问题
    private String userDec;
    private Integer userMoney;
}
