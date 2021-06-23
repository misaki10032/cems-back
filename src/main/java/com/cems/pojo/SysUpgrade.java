package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUpgrade {
    private Integer id;
    private Integer adminId;
    private String adminTarget;
    private String adminNow;
    private String status;
    private String adminReason;
}
