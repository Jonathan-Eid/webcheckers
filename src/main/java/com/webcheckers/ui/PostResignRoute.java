package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;

/**
 * Created by Juna on 11/17/2017.
 */
public class PostResignRoute implements Route {
    private Gson gson;
    private PlayerLobby playerLobby;



    public PostResignRoute(Gson gson, PlayerLobby playerLobby) {
        this.gson = gson;
        this.playerLobby = playerLobby;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player quitter = session.attribute(USER_ATTR);
        Player opponent = playerLobby.getPlayerOpponent(quitter);
        Message message;
        if (playerLobby.isInGame(opponent) && playerLobby.isInGame(quitter)){
            playerLobby.endResignedGame(quitter);
            message = new Message("Resigned", Message.type.info);
        }
        else{
            message = new Message("Error: Could not resign", Message.type.error);
        }
        return gson.toJson(message, Message.class);
    }
}

