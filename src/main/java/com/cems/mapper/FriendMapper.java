package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.UniUserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName FriendMapper
 * @Author 陈新予(blank)
 * @Date 2021/6/28
 * @Version 1.0
 */
@Mapper
@Component
public interface FriendMapper extends BaseMapper<UniUserFriend> {

    void addFriend(@Param("userId") int userId, @Param("friendId") int friendId);

    List<UniUserFriend> getMsg(@Param("userId") int userId, @Param("friendId") int friendId);

    int countFocus(int id);

    int countFans(int id);

}
