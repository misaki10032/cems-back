package com.cems.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAdminInfo {
    private Integer id;
    private Integer adminId;
    private String adminPhone;
    private String adminHouse;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adminBirth;
    private String adminEmail;
    private String adminPwdProtect;
}
