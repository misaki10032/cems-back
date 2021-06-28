package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName UniEntrust
 * @Author 陈新予(blank)
 * @Date 2021/6/25
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniEntrust {
    private int id;
    private String entData;
    private String entConsignor;
    private String entAgent;
    private String entState;
    private String entType;
    private String entPlan;
    private String gmtCreate;
    private String gmtEnd;
    private int gmtMoney;
}
