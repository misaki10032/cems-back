package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message {
    private Integer id;
    private Integer userId;
    private String userMessage;
    private String userTime;
    private boolean check;
    private boolean del;
}
