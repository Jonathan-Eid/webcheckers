package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_NAME_ATTR;
import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;


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



    public String error(String error, Request request, Response response){
        final Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        Player player = session.attribute("player");
        vm.put("message", true);
        vm.put("error", error);
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE_VAL);
        vm.put(PostSignInRoute.PLAYER_SIGNED_IN_ATTR, true);
        vm.put(GetHomeRoute.PLAYER_NAME_ATTR, player.getName());
        vm.put(PLAYER_LIST_ATTR, playerLobby.playerList(player.getName()));
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    public Object handle(Request request, Response response) {
        return error("Test", request, response);
        /*
        LOG.config("GetGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        vm.put("viewMode", "PLAY");
        Player player;
        Player opponent;

        if (request.session().attribute("signedin")) {
            if (request.queryParams("name") != null) { //Player who clicked button.
                player = playerLobby.getPlayer(request.session().attribute("player"));
                opponent = playerLobby.getPlayer(request.queryParams("name"));
                if ((!(playerLobby.isInGame(player))) && (!(playerLobby.isInGame(opponent)))) {
                    playerLobby.addToGame(player, opponent);
                    response.redirect("/game");
                }
                else if (!playerLobby.isInGame(player)){
                    //Player is already in a game.
                    error(player.getName() + " is already in a game", request, response);
                }
                else {
                    //Opponent is already in a game.
                    error(opponent.getName() + " is already in a game", request, response);
                }
            }
        }
        if (session.attribute("player") != null){ //Current player has to be signed in.
            if (request.queryParams("opponent") != null){
                //This is the player who clicked the button.

                player = session.attribute("player");
                String opponentName = request.queryParams("opponent");
                opponent = playerLobby.getPlayer(opponentName);

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
        vm.put("activeColor", Piece.color.RED);

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    */
    }
}
