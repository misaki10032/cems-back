package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName ForumComment
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ForumComment {
    private int id;
    private int artId;
    private int commUser;
    private String commData;
    private Date gmtCreate;
}
