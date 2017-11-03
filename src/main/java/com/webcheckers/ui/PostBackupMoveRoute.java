package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Juna on 11/3/2017.
 */
public class PostBackupMoveRoute implements Route {

    private Gson gson;

    public PostBackupMoveRoute(Gson gson){
        this.gson = gson;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
