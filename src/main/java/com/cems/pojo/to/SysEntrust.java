package com.cems.pojo.to;

/*
 *
 *@auth  biewenzhao
 *@date 2021/6/21  13:30
 */

import com.cems.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysEntrust {

    private Integer entrustId;
    //委托类容
    private String entData;
    //委托人id
    private Integer entConsignor;
    //代理人id
    private Integer entAgent;
    //委托状态
    private String entState;
    //委托类型
    private String entType;
    //类型id
    private String enTypeId;
    //委托计划
    private String entPlan;
    //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date gmtCreate;
    //最迟时间
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date gmtEnd;
    //发布时间1
    private String gmtCreate1;
    //最迟时间1
    private String gmtEnd1;
    //成交金额

    private Integer entMoney;
    //接单人电话号
    private String userPhone;

    public void setGmtCreate1(Object date) {
        if (date instanceof Date)
            this.gmtCreate1 = new DateUtil().dateToString((Date) date);
        else {
            this.gmtCreate1 = (String) date;
        }
    }

    public void setGmtEnd1(Object date) {
        if (date instanceof Date)
            this.gmtEnd1 = new DateUtil().dateToString((Date) date);
        else
            this.gmtEnd1 = (String) date;
    }
}
