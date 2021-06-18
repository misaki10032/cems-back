package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName ComEntrustType
 * @Author 陈新予(blank)
 * @Date 2021/6/18
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComEntrustType {
    private int id;
    private String entType;
}
