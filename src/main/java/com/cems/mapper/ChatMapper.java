package com.cems.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cems.pojo.ForumArticle;
import com.cems.pojo.netty.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ChatMapper extends BaseMapper<ChatMessage> {


    List<ChatMessage> selSomeChat();

    int addOneChat(ChatMessage chatMessage);
}
