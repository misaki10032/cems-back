package com.cems.pojo.uni;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/29  10:50
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniUpUserSole {
    private  Integer id;
    private String userRole;
    private Integer money;
    private Integer upMoney;

}
