package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName UniPage
 * @Author 陈新予(blank)
 * @Date 2021/6/26
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniPage {
    private String pageIndex;
    private String pageSize;
    private String entPlan;
    private String text;
}
