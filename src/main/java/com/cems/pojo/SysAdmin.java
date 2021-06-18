package com.cems.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName ComUser
 * @Author 陈新予(blank)
 * @Date 2021/6/6
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysAdmin {
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;
    private String adminNum;
    private String adminPwd;
    private String adminLevel;
    private String adminStatus;
}
