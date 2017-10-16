package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Message;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import jdk.nashorn.internal.parser.JSONParser;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostValidateMoveRoute implements Route {


    private PlayerLobby playerLobby;
    private Gson gson;

    public PostValidateMoveRoute(PlayerLobby playerLobby, Gson gson) {
        this.playerLobby = playerLobby;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Move move = gson.fromJson(request.body(), Move.class);
        Board board = session.attribute("board");
        Message message;
        if (board.isValidMove(move)){
             message = new Message("Valid Move", Message.type.INFO);
        }
        else{
            message = new Message("Invalid Move", Message.type.ERROR);
        }
        return null;
    }
}
