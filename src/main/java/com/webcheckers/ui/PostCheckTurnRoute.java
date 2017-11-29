package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.*;


import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;
import static com.webcheckers.ui.PostSubmitTurnRoute.GAME_OVER_ATTR;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostCheckTurnRoute implements Route {

    private Gson gson;
    private PlayerLobby playerLobby;
    GameCenter gameCenter;

    static final String OPPONENT_RESIGNED_ATTR = "opponentResigned";


    /**
     * Constructor
     * @param gson Gson Interpreter
     */
    public PostCheckTurnRoute(Gson gson, PlayerLobby playerLobby, GameCenter gameCenter) {
        this.gson = gson;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
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
        Message message = new Message("true", Message.type.info);
        if (game.isGameOver()){
            session.attribute(GAME_OVER_ATTR, true);
        }
        else if (!gameCenter.isInGame(player)){
            //Opponent resigned.
            session.attribute(OPPONENT_RESIGNED_ATTR, true);
        }
        else if (!game.checkTurn(player)){
            message = new Message("false", Message.type.info);
        }
        return gson.toJson(message, Message.class);
    }
}
