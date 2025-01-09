package com.burnsena.chess.messages;

import com.burnsena.chess.Game;
import com.burnsena.chess.GameManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

public record InitMessage(MessageType type, Integer data) implements Message, MessageData<Integer> {

    @Override
    public void handle(GameManager gameManager, WebSocketSession session) throws IOException {
        System.out.println("handling msg: " + this.type);
        ObjectMapper objectMapper = new ObjectMapper();
        if (gameManager.getPendingUser() == null) {
            gameManager.setPendingUser(session);
            String waitPayload = objectMapper.writeValueAsString(new Response<>(
                    MessageType.WAIT_PLAYER,
                    "waiting for opponent"
            ));
            session.sendMessage(new TextMessage(waitPayload));
            return;
        }

        Game game = Game.builder()
                .duration(data)
                .player1(gameManager.getPendingUser())
                .player2(session)
                .build();
        gameManager.addGame(game);
        //send their sides
        String whitePayload = objectMapper.writeValueAsString(new Response<>(
                MessageType.WHITE,
                Map.of("gameId", game.getId(), "opponentSessionId", gameManager.getPendingUser().getId())
        ));
        String blackPayload = objectMapper.writeValueAsString(new Response<>(
                MessageType.BLACK,
                Map.of("gameId", game.getId(), "opponentSessionId", session.getId())
        ));
        session.sendMessage(new TextMessage(whitePayload));
        gameManager.getPendingUser().sendMessage(new TextMessage(blackPayload));
        gameManager.setPendingUser(null);
    }
}
