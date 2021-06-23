package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName LevelUpDTO
 * @Author 陈新予(blank)
 * @Date 2021/6/23
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LevelUpDTO {
    private int id;
    private String adminNum;
    private String target;
    private String adminNow;
    private String desc;
}
