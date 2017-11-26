package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;
import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_SIGNED_IN_ATTR;
import static spark.Spark.halt;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostCheckTurnRoute implements Route {

    private Gson gson;
    private PlayerLobby playerLobby;

    static final String OPPONENT_RESIGNED_ATTR = "opponentResigned";
    /**
     * Constructor
     * @param gson Gson Interpreter
     */
    public PostCheckTurnRoute(Gson gson, PlayerLobby playerLobby) {
        this.gson = gson;
        this.playerLobby = playerLobby;
    }


    /**
     * Check if the other player's turn is over.
     * @param request
     * @param response
     * @return Message either containing true (if the other player's turn is over) or false.
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player player = session.attribute(USER_ATTR);
        Game game = session.attribute(GAME_ATTR);
        Message message;
        if (!playerLobby.isInGame(playerLobby.getPlayerOpponent(player))){
            //Opponent resigned.
            session.attribute(OPPONENT_RESIGNED_ATTR, true);
            message = new Message("true", Message.type.info);
        }
        else if (game.checkTurn(player)){
            message = new Message("true", Message.type.info);
        }
        else {
            message = new Message("false", Message.type.info);
        }
        return gson.toJson(message, Message.class);
    }
}
