package com.burnsena.chess;

import com.burnsena.chess.messages.Message;
import com.burnsena.chess.messages.MessageDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GameManager gameManager = new GameManager();

    public WebSocketHandler(){
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Message.class, new MessageDeserializer());
        objectMapper.registerModule(module);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Websocket connection established with session id: " + session.getId());
        gameManager.addUser(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        try {
            Message msg = objectMapper.readValue(payload, Message.class);
            gameManager.handleMessage(session, msg);
        }catch (Exception e){
            e.printStackTrace();
            session.sendMessage(new TextMessage(e.getMessage()));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Websocket connection: '" + session + "' is closed: " + status.getReason());
        gameManager.removeUser(session);
    }
}
