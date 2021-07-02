package com.cems.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysNotice {
    private Integer noticeId;
    private String noticeContent;
    private String noticeBegin;
    private String noticeEnd;
    private Integer adminId;
}
