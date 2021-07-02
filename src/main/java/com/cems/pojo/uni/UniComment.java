package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName UniComment
 * @Author 陈新予(blank)
 * @Date 2021/7/2
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniComment {
    private int cid;
    private int artId;
    private int cUserId;
    private String cUserName;
    private String commData;
    private String cGmtCreate;
    private List<UniReply> replies;
}
