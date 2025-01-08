package com.burnsena.chess.messages;

import java.util.Map;

public interface Message {
    public MessageType type();
    public void handle();
}
