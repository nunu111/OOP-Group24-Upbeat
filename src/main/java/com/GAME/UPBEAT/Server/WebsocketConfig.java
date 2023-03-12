package com.GAME.UPBEAT.Server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { //เพิ่ม path สำหรับ input output
        registry.enableSimpleBroker("/topic/"); //model to view
        registry.setApplicationDestinationPrefixes("/app"); //prefix ขาเข้า client -> server (ส่วนตัว)
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // ws://ipadd:port/demo-websocket
        registry.addEndpoint("/demo-websocket").setAllowedOriginPatterns("*"); //client ที่ไหนก็เข้าได้

    }
}
