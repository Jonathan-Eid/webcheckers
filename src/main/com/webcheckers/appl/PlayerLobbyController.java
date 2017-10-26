package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.ui.WebServer;
import spark.Response;

import static spark.Spark.halt;

public class PlayerLobbyController {
    PlayerLobby playerLobby;


    public PlayerLobbyController(PlayerLobby playerLobby){
        this.playerLobby = playerLobby;
    }

    public void redirectHomeToGame(Player player, Response response){
        if (playerLobby.isInGame(player)){
            response.redirect(WebServer.GAME_URL);
            halt();
        }
    }
}
