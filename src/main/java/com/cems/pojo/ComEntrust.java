package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName ComEntrust
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComEntrust {
    private int id;
    private String entData;
    private int entConsignor;
    private int entAgent;
    private String entState;
    private String entTypeId;
    private String entPlan;
    private String gmtCreate;
    private String gmtEnd;
    private String entMoney;
}
