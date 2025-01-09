package com.burnsena.chess.messages;

import com.burnsena.chess.GameManager;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface Message {
    MessageType type();
    void handle(GameManager gameManager, WebSocketSession session) throws IOException;
}
