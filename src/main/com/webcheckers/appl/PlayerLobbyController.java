package webcheckers.appl;

import webcheckers.model.Player;
import webcheckers.ui.WebServer;
import spark.Response;
import webcheckers.appl.PlayerLobby;

import static spark.Spark.halt;

/**
 * A GRASP controller object that handles non-UI events that affect the PlayerLobby. For example, it allows players
 * to enter games that were started by other players.
 * Created by Andy
 */
public class PlayerLobbyController {
    PlayerLobby playerLobby;

    /**
     * constructor for PlayerLobbyController with only one argument
     * @param playerLobby the playerLobby that provides the player information on which PlayerLobbyController acts
     */
    public PlayerLobbyController(PlayerLobby playerLobby){
        this.playerLobby = playerLobby;
    }

    /**
     * Allows a home route to redirect a player to a game route
     * @param player the player being redirected
     * @param response the spark HTTP response
     */
    public void redirectHomeToGame(Player player, Response response){
        if (playerLobby.isInGame(player)){
            response.redirect(WebServer.GAME_URL);
            halt();
        }
    }
}
