package com.cems.pojo.netty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName Message
 * @Author 陈新予(blank)
 * @Date 2021/7/6
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMessage {
    private int id;
    private String userName;
    private String userMsg;
    private String dateTime;
    private int userId;
}
