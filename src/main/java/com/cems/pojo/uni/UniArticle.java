package com.cems.pojo.uni;

import com.cems.pojo.ForumComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName UniArticle
 * @Author 陈新予(blank)
 * @Date 2021/7/2
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniArticle {
    private int id;
    private int aUserId;
    private String aUserName;
    private String foTitle;
    private String foData;
    private String status;
    private String aGmtCreate;
    private List<UniComment> comments;

}
