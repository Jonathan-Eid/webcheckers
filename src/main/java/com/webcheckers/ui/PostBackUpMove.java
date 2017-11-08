package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

public class PostBackUpMove implements Route {

    private PlayerLobby playerLobby;
    private Gson gson;

    public PostBackUpMove(PlayerLobby playerLobby, Gson gson) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Game game = session.attribute(GAME_ATTR);
        Message message;
        try{
            game.undo();
            message = new Message("Undid move", Message.type.info);
        }catch (IllegalStateException e){
            message = new Message("No moves to undo", Message.type.error);
        }
        return gson.toJson(message, Message.class);
    }
}
