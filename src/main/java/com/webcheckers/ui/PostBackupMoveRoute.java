package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.appl.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

/**
 * This class handles allowing the user to undo a move.
 */
public class PostBackUpMoveRoute implements Route {

    private Gson gson;

    /**
     *
     * @param gson Gson interpreter.
     */
    public PostBackUpMoveRoute(Gson gson) {
        this.gson = gson;
    }

    /**
     * Tries to undo the last move made. Checks if any errors occur on the backend (i.e. no moves to undo)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        //Fetch the game object from the session.
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
