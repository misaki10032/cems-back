package com.cems.service;

import com.cems.pojo.netty.ChatMessage;

import java.util.List;

public interface ChatService {

    List<ChatMessage> selSomeChat();

    int addOneChat(ChatMessage chatMessage);
}
