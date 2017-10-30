package webcheckers.ui;

import webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by dis446 on 10/16/17.
 */
public class PostCheckTurnRoute implements Route {

    private PlayerLobby playerLobby;

    public PostCheckTurnRoute(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
