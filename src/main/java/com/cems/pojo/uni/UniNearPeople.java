package com.cems.pojo.uni;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName UniNearPeople
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UniNearPeople {
    private String userName;
    private String userSex;
    private String userDistance;
    private String userId;
}
