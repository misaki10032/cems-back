package com.cems.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.SysAdmin;
import com.cems.pojo.to.ComUser;
import com.cems.pojo.to.ComUserInfo;
import com.cems.pojo.to.SysEntrust;
import com.cems.service.ComUserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName UserMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/7
 * @Version 1.0
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<ComUser> {

    ComUser selOneUser(int id);

    List<ComUser> getUsers();

    List<ComUserInfo> getUsersInfo();

    void killUser(@Param("id") int id, @Param("status") String status);

    List<ComUser> getUserLike(@Param("phone")String phone,@Param("status")String status);
    /**用户申诉账号密码验证*/
    ComUser judgeAP(Map<String,Object> map);

    ComUser getUserNum(String num);

    List<SysEntrust> byEntrustByType(Integer typeID);
}
