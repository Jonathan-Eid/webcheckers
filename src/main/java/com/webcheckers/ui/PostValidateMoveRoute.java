package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Game;
import com.webcheckers.model.Board;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Move;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostValidateMoveRoute implements Route {

    private Gson gson;

    /**
     *
     * @param gson Gson interpreter.
     */
    public PostValidateMoveRoute(Gson gson) {
        this.gson = gson;
    }

    /**
     * Validate a single move that the user is trying to make.
     * @param request
     * @param response
     * @return Message containing info if the move is valid or error if not.
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        //Fetch the move from the server
        Move move = gson.fromJson(request.body(), Move.class);
        //Fetch the game from the session.
        Game game = session.attribute(GAME_ATTR);
        //Fetch the board from the game
        Board board = game.getBoard();
        Message message;
        Move.moveType moveType = board.isValidMove(move);

        if (!moveType.equals(Move.moveType.INVALID)){
            //Move is valid. Set the type of the move so that the makeMove method can handle it properly.
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
