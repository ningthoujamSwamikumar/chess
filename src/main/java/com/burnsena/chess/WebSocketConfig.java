package com.burnsena.chess;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler(), "/ws").setAllowedOrigins("*");
    }

    @Bean
    public org.springframework.web.socket.WebSocketHandler handler(){
        return new WebSocketHandler();
    }
}
