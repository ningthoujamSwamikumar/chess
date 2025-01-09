package com.burnsena.chess.messages;

import com.burnsena.chess.GameManager;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public record MoveMessage(MessageType type, Map<String, String> data) implements Message, MessageData {
    @Override
    public void handle(GameManager gameManager, WebSocketSession session) {
        System.out.println("handling msg: " + this.type + ", with data: " + this.data);
    }
}
