package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Move;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostValidateMoveRoute implements Route {


    private PlayerLobby playerLobby;

    public PostValidateMoveRoute(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
