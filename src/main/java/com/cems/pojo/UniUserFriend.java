package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName UniUserFriend
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniUserFriend {
    private int id;
    private int userId;
    private int friendId;
}
