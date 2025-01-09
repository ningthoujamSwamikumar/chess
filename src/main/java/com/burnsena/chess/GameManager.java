package com.burnsena.chess;

import com.burnsena.chess.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Data
@NoArgsConstructor
public class GameManager {
    private final List<Game> games = new ArrayList<>();
    private WebSocketSession pendingUser;
    private final List<WebSocketSession> users = new ArrayList<>();

    public void addGames(Game game){
        this.games.add(game);
    }

    public void addUser(WebSocketSession user){
        this.users.add(user);
    }

    public void removeUser(WebSocketSession session) {
        users.remove(session);
    }

    public void handleMessage(WebSocketSession session, Message msg) throws IOException {
        System.out.println("Message from " + session.getId() + ": " + msg);
        msg.handle(this, session);
    }

    public void addGame(Game game){
        this.games.add(game);
    }

    public void removeGame(Game game){
        this.games.remove(game);
    }

    public void removeGame(String gameId) throws Exception {
        Game game = findGameById(gameId).orElseThrow(()->new RuntimeException("Invalid argument, gameId="+gameId));
        this.games.remove(game);
    }

    private Optional<Game> findGameById(String id){
        for(Game game : this.games){
            if(game.getId().equals(id)) return Optional.of(game);
        }
        return Optional.empty();
    }
}
