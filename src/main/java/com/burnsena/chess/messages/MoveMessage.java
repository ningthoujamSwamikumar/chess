package com.burnsena.chess.messages;

import java.util.Map;

public record MoveMessage(MessageType type, Map<String, String> data) implements Message, MessageData {
    @Override
    public void handle() {
        System.out.println("handling msg: " + this.type + ", with data: " + this.data);
    }
}
