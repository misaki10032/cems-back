package com.cems.service.impl;

import com.cems.mapper.ChatMapper;
import com.cems.pojo.netty.ChatMessage;
import com.cems.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatMapper chatMapper;
    @Override
    public List<ChatMessage> selSomeChat() {
        return chatMapper.selSomeChat();
    }

    @Override
    public int addOneChat(ChatMessage chatMessage) {
        return chatMapper.addOneChat(chatMessage);
    }
}
