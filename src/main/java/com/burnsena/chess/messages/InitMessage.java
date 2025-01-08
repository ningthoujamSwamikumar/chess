package com.burnsena.chess.messages;

public record InitMessage(MessageType type) implements Message {
    @Override
    public void handle() {
        System.out.println("handling msg: " + this.type);
    }
}
