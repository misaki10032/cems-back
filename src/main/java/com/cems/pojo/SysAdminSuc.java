package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAdminSuc {
    private Integer adminId;
    private Integer loginNum;
    private Integer operateNum;
    private String adminType;
}
