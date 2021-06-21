package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName CommentReply
 * @Author 陈新予(blank)
 * @Date 2021/6/20
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentReply {
    private int id;
    private int commUser;
    private int commReUser;
    private String commReData;
    private String commReply;
    private Date gmtCreate;
}
