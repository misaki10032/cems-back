package com.cems.pojo;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/19  19:43
 */


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
/**
 * 退钱类
 */
public class MoneyBack {
  /**
   *     ////委托id->entrustId
   *     //委托状态  entPlan
   *     //委托人的ID   entConsignor
   *     //代理人的id  entAgent
   *     //酬劳报酬   entMoney
   */
  private   Integer entrustId;
    private  String entPlan;
    private  Integer entConsignor;
    private  Integer entAgent;
    private  Double entMoney;
}
