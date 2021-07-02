package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName UniReply
 * @Author 陈新予(blank)
 * @Date 2021/7/2
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniReply {
    private int rid;
    private String commReData;
    private int rCommuser;
    private String rCommuserName;
    private int commReUser;
    private String commReUserName;
    private int commReply;
    private String rGmtCreate;
}
