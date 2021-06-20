package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName FormArticle
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FormArticle {
    private String title;
    private String status;
    private int pageNum;
    private int pageSize;
}
