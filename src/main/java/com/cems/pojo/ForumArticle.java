package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ForumArticle
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ForumArticle {
    private int id;
    private int userId;
    private String foTitle;
    private String foData;
    private String status;
    private String gmtCreate;
    private boolean del;
    private String pname;
}
