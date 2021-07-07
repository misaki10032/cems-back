package com.cems.controller.uniApp.Info;

import com.cems.pojo.netty.ChatMessage;
import com.cems.pojo.uni.UniArticle;
import com.cems.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("uniApp")
public class Uni_ChatController {
    @Autowired
    ChatService chatService;

    @GetMapping("selSomeChat")
    public Map<String,Object> selSomeChat(){
        Map<String,Object> map = new ConcurrentHashMap<>();
        try{
            List<ChatMessage> chatMessages = chatService.selSomeChat();
            map.put("code",200);
            map.put("msg","查询成功!");
            map.put("article",chatMessages);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","查询失败");
        }
        return map;
    }
}
