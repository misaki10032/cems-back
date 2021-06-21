package com.cems.pojo.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Appeal {
    String phone;
    String psw;
    String desc;
    String acc;
    String phoneTwo;
    String email;
}
