package com.cems.netty;

import com.alibaba.fastjson.JSON;
import com.cems.pojo.netty.ChatMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用来保存所有的客户端连接
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String nowTime = LocalDateTime.now().format(dtf);
        //当接收到数据后会自动调用
        String text = msg.text();
        System.out.println("接收到的消息数据为："+text);
        String[] split = text.split("&&");
        for(Channel client : clients){
            //数据库信息存储
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setUserName(split[0])
                    .setUserMsg(split[1])
                    .setDateTime(nowTime);
            //将消息发送到所有的客户端
//            client.writeAndFlush(new TextWebSocketFrame(split[0]+"&&"+nowTime+"&&"+split[1]));
            client.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatMessage)));
        }
    }
    //当有新的客户端连接服务之后，会自动调用这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将新的通道加入到clients
        clients.add(ctx.channel());
    }


}
