package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Message;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;
import static spark.Spark.halt;

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
        Game game = session.attribute(GAME_ATTR);
        Board board = game.getBoard();
        Message message;
        Move.moveType moveType = board.isValidMove(move);
        if (!moveType.equals(Move.moveType.INVALID)){
            move.setType(moveType);
            game.makeMove(move);
            message = new Message("", Message.type.info);
        }
        else{
            message = new Message("Invalid Move", Message.type.error);
        }
        return gson.toJson(message, Message.class);
    }
}
