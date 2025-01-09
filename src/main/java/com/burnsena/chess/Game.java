package com.burnsena.chess;

import com.github.bhlangonijr.chesslib.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Game {
    private final String id = UUID.randomUUID().toString();
    private final WebSocketSession player1;
    private final WebSocketSession player2;
    private final Board board = new Board();
    private final List<String> moves = new ArrayList<>();
    private final Date startTime;
    private final int duration;
}
