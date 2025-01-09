package com.burnsena.chess.messages;

public enum MessageType {
    //user messages
    INIT_GAME, MOVE, SURRENDER,
    //in game chats
    CHAT,
    //responses
    BLACK, WHITE, WAIT_PLAYER, OK, ERROR, INVALID_ARG, INVALID_MOVE, PLAYER_DISCONNECTED;
}
