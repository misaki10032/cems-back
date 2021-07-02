package com.cems.pojo.uni;

import com.cems.pojo.to.ComUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName UniMyFriend
 * @Author 陈新予(blank)
 * @Date 2021/6/30
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniMyFriend {
    private int userId;
    private List<ComUser> friends;
}
