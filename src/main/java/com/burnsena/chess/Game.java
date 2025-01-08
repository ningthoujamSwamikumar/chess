package com.burnsena.chess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private String id;
    private WebSocketSession player1;
    private WebSocketSession player2;
    private String board;
    private List<String> moves;
    private Date startTime;
}
