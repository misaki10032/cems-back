package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName PageTo
 * @Author 陈新予(blank)
 * @Date 2021/6/13
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageTo {
    private int pageNum;
    private int pageSize;
}
