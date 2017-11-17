package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

/**
 * Created by Juna on 11/17/2017.
 */
public class PostResignRoute implements Route {
    private Gson gson;



    public PostResignRoute(Gson gson) {
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        //Fetch the game object from the session.
       // Game game = session.attribute(GAME_ATTR);
        Message message;
        message = new Message("Undid move", Message.type.info);
        //message = new Message("No moves to undo", Message.type.error);
        return gson.toJson(message, Message.class);
        //TODO Fix Internal Server Error
    }
}

