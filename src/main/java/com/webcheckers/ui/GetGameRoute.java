package com.webcheckers.ui;

import com.webcheckers.model.Game;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.ui.PostSignInRoute.*;


/**
 * Renders the game view page
 * Created by Andrew Didycz, Disney, Ani, Johnny on 10/12/17.
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    private enum viewMode {PLAY, SPECTATOR, REPLAY};
    private final TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    static final String VIEW_MODE_ATTR = "viewMode";
    static final String GAME_ATTR = "game";
    static final String CURRENT_PLAYER_ATTR = "currentPlayer";
    static final String RED_PlAYER_ATTR = "redPlayer";
    static final String WHITE_PLAYER_ATTR = "whitePlayer";
    static final String ACTIVE_COLOR_ATTR = "activeColor";
    static final String BOARD_VIEW_ATTR = "board";
    static final String MESSAGE_ATTR = "message";

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /game} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     * @param playerLobby contains information on players
     */
    public GetGameRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
        Objects.requireNonNull( templateEngine, "Template Engine must not be null");
        Objects.requireNonNull(playerLobby, "PlayerLobby must not be null");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }


    /**
     * Renders the home page with a given error message. This is called when something goes wrong but not fatally.
     * @param error String message to display to user
     * @param request the HTTP request
     * @param response the HTTP response
     * @return
     */
    public String error(String error, Request request, Response response){
        final Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        Player player = session.attribute("player");
        vm.put("message", true);
        vm.put("error", error);
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE_VAL);
        vm.put(USER_SIGNED_IN_ATTR, true);
        vm.put(USER_ATTR, player.getName());
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

        LOG.config("GetGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();

        //Fetch game and players from the session
        Game game = session.attribute(GAME_ATTR);
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player currentPlayer = session.attribute(USER_ATTR);

        //Add to the viewMap all attributes that won't change.
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE_VAL);
        vm.put(VIEW_MODE_ATTR, viewMode.PLAY);
        vm.put(RED_PlAYER_ATTR, player1);
        vm.put(WHITE_PLAYER_ATTR, player2);
        vm.put(CURRENT_PLAYER_ATTR, currentPlayer);

        if (currentPlayer.equals(player1)){
            //Current user is the first player.
            if (game.checkTurn(currentPlayer)){
                vm.put(ACTIVE_COLOR_ATTR, Piece.color.RED);
            }
            else{
                vm.put(ACTIVE_COLOR_ATTR, Piece.color.WHITE);
            }
            vm.put(BOARD_VIEW_ATTR, game.getBoard());
        }
        else if (currentPlayer.equals(player2)){
            //Current user is the second player.
            if (game.checkTurn(currentPlayer)){
                vm.put(ACTIVE_COLOR_ATTR, Piece.color.WHITE);
            }
            else{
                vm.put(ACTIVE_COLOR_ATTR, Piece.color.RED);
            }
            vm.put(BOARD_VIEW_ATTR, game.getBoard().reverse());
        }
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
