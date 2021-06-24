package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName SysAdminInfo
 * @Author 陈新予(blank)
 * @Date 2021/5/16
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAdminInfoBig {
    private String id;
    private String adminNum;
    private String adminPwd;
    private String adminLevel;
    private String adminStatus;
    private Integer adminId;
    private String adminPhone;
    private String adminHouse;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adminBirth;
    private String adminEmil;
    private String adminPwdProtect;
}
