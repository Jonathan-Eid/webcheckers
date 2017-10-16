package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 *
 * Created by Andrew Didycz, Disney, Ani, Johnny on 10/12/17.
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private enum viewMode {PLAY, SPECTATOR, REPLAY};
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /game} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     * @param playerLobby contains information on players
     */
    public GetGameRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {
        LOG.config("GetGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        Player player; //Player who hit the button.
        Player opponent; //Player who's name was clicked.

        if (session.attribute("player") != null){ //Current player has to be signed in.
            if (request.queryParams("opponent") != null){
                //This is the player who clicked the button.
                player = session.attribute("player");
                String opponentName = request.queryParams("opponent");
                opponent = playerLobby.getPlayer(opponentName);
                if (playerLobby.isInGame(player)){
                    session.attribute("inGameError", true);
                }
                if (playerLobby.isInGame(opponent)){
                    session.attribute("inGameError", true);
                }
            }
            else{ //This is the player who's name was clicked.
                player = session.attribute("player");
                opponent = null;
            }
        }
        else {
            throw new NullPointerException("player attribute is null");
        }

        vm.put("currentPlayer", player);
        vm.put("viewMode", viewMode.PLAY);
        vm.put("redPlayer", player);
        vm.put("whitePlayer", opponent);
        vm.put("activeColor", Piece.colors.RED);

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }


}
