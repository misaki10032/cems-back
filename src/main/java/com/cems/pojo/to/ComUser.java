package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
}
