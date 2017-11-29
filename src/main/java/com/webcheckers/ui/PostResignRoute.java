package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.PostCheckTurnRoute.OPPONENT_RESIGNED_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;

/**
 * Created by Juna on 11/17/2017.
 */


public class PostResignRoute implements Route {
    private Gson gson;
    private PlayerLobby playerLobby;
    GameCenter gameCenter;


    static final String RESIGNED_ATTR = "resigned";


    public PostResignRoute(Gson gson, PlayerLobby playerLobby, GameCenter gameCenter) {
        this.gson = gson;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
    }


    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     *
     * This handle is called by the player when they want to resign.
     * There is an error message when a player tries to resign when the opponent has already resigned.
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player quitter = session.attribute(USER_ATTR);
        Player opponent = playerLobby.getPlayerOpponent(quitter);
        Message message;
        if (gameCenter.isInGame(opponent) && gameCenter.isInGame(quitter)){
            playerLobby.endResignedGame(quitter);
            session.attribute(RESIGNED_ATTR, true);
            message = new Message("Resigned", Message.type.info);
        }
        else{
            session.attribute(OPPONENT_RESIGNED_ATTR, true);
            message = new Message("Opponent has already resigned", Message.type.error);
        }
        return gson.toJson(message, Message.class);
    }
}

