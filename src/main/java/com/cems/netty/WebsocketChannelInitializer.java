package com.cems.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    //初始化通道，在这个方法中加载对应的ChannelHandler
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取管道，将一个一个的ChannelHandler添加到管道中
        ChannelPipeline pipeline = socketChannel.pipeline();
        //添加一个http的编解码器
        pipeline.addLast(new HttpServerCodec());
        //添加一个用用于支持大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加一个聚合器，这个聚合器主要是将HttpMessage聚合成FullHttpRequest/Response
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        //需要指定接受请求的路由，必须使用以WS后缀结尾的url才能访问
        pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));
        //添加自定义Handler
        pipeline.addLast(new ChatHandler());
    }
}
