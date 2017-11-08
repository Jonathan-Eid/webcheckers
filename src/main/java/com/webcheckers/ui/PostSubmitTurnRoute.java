package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

/**
 * Created by Juna on 11/3/2017.
 */
public class PostSubmitTurnRoute implements Route {

    private Gson gson;

    /**
     * Constructor
     * @param gson Gson interpreter.
     */
    public PostSubmitTurnRoute(Gson gson){
        this.gson = gson;
    }

    /**
     * Submit a turn. This finalized one player's turn by committing them to every move they've made this turn and
     * switiching the turn to the other player.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Game game = session.attribute(GAME_ATTR);
        game.finishTurn();
        game.startTurn();
        return gson.toJson(new Message("Valid Turn", Message.type.info), Message.class);
    }
}
