package com.cems;

import com.cems.netty.WebSocketNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CemsApplication.class, args);
        WebSocketNettyServer.openNettyServer();
    }

}
