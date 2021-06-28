package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName UniAddEntrust
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniAddEntrust {
    private String type;
    private String data;
    private String money;
    private String time;
    private String id;
}
