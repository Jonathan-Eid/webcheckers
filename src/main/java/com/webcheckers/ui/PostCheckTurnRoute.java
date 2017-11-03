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
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostCheckTurnRoute implements Route {

    private Gson gson;
    private PlayerLobby playerLobby;


    public PostCheckTurnRoute(PlayerLobby playerLobby, Gson gson) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player player = session.attribute(USER_ATTR);
        Game game = session.attribute(GAME_ATTR);
        Message message;
        if (game.checkTurn(player)){
            message = new Message("true", Message.type.info);
        }
        else{
            message = new Message("false", Message.type.info);
        return gson.toJson(message, Message.class);
        }
        return message;
    }
}
