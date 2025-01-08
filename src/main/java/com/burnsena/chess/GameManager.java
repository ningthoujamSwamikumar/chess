package com.burnsena.chess;

import com.burnsena.chess.messages.Message;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Game> games = new ArrayList<>();
    private WebSocketSession pendingUser;
    private List<WebSocketSession> users = new ArrayList<>();

    public void addGames(Game game){
        this.games.add(game);
    }

    public void addUser(WebSocketSession user){
        this.users.add(user);
    }

    public void removeUser(WebSocketSession session) {
        users.remove(session);
    }

    public void handleMessage(WebSocketSession session, Message msg) {
        System.out.println("Message from " + session.getId() + ": " + msg);
        msg.handle();
    }
}
