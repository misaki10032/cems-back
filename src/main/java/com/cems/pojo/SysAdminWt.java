package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author:wt
 * @Date: 2021/6/21 20:38
 * @Description: 双表查询
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAdminWt {
    private Integer id;//info表id
    private Integer adminId;
    private String admin_phone;
    private String adminHouse;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adminBirth;
    private String adminEmail;
    private String adminPwdProtect;
    private String adminNum;
    private String adminLevel;
    private String adminStatus;
}
