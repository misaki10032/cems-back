package com.cems.netty;

import com.cems.service.ChatService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;

public class WebSocketNettyServer {
    public static void openNettyServer(){
        // 创建两个线程池
        NioEventLoopGroup mainGrp = new NioEventLoopGroup(); //主线程池
        NioEventLoopGroup subGrp = new NioEventLoopGroup();//从线程池
        try {
            //创建Netty服务对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //初始化服务器对象
            serverBootstrap
                    //指定使用上面创建的线程池
                    .group(mainGrp,subGrp)
                    //指定Neyyt通道类型
                    .channel(NioServerSocketChannel.class)
                    //指定通道初始化器来加载当Channel收到消息事件后
                    .childHandler(new WebsocketChannelInitializer());
            //绑定服务器端口，已同步方式启动服务器
            ChannelFuture future = serverBootstrap.bind(9000).sync();
            //等待服务器关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭服务器
            mainGrp.shutdownGracefully();
            subGrp.shutdownGracefully();
        }
    }
}
