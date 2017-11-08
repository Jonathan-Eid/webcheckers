package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.appl.Message;
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

    /**
     * Constructor
     * @param gson Gson Interpreter
     */
    public PostCheckTurnRoute(Gson gson) {
        this.gson = gson;
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
        if (game.checkTurn(player)){
            message = new Message("true", Message.type.info);
        }
        else {
            message = new Message("false", Message.type.info);
        }
        return gson.toJson(message, Message.class);
    }
}
