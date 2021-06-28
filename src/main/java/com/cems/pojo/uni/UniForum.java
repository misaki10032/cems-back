package com.cems.pojo.uni;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/28  17:50
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniForum {
    private Integer id;
    private String title;
    private String data;
}
