package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName LoginAdmin
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUser {
    private Integer id;
    private String userPhone;
    private String psw;
    private String newPsw;
}
