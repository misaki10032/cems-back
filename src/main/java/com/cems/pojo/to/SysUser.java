package com.cems.pojo.to;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/28  15:43
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser {
    private Integer id;
    private String userPhone;
    private String userPwd;
    private String userRole;
    private String status;
}
