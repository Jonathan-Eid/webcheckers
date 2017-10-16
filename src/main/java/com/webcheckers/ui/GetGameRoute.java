package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;


/**
 * Renders the game view page
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

    private static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
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

        LOG.config("GetGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE_VAL);
        vm.put("viewMode", "PLAY");
        Player player;
        Player opponent;

        if (request.session().attribute("player") != null) { //Check that player is logged in.
            player = request.session().attribute("player");
            if (request.queryParams("opponent") != null) {
                opponent = playerLobby.getPlayer(request.queryParams("opponent"));
                if ((!(playerLobby.isInGame(player))) && (!(playerLobby.isInGame(opponent)))) {
                    player.setColor(Piece.color.RED);
                    opponent.setColor(Piece.color.WHITE);
                    playerLobby.addToGame(player, opponent);
                    response.redirect("/game");
                }
                else if (playerLobby.isInGame(player)){
                    //Player is already in a game.
                    error(player.getName() + " is already in a game", request, response);
                }
                else if (playerLobby.isInGame(opponent)) {
                    //Opponent is already in a game.
                    error(opponent.getName() + " is already in a game", request, response);
                }
            }
            else{
                player = session.attribute("player");
                Piece.color color = player.getColor();
                if (color.equals(Piece.color.WHITE)) {
                    opponent = player;
                    Map<Player, Player> hashMap = playerLobby.getinGameMap();
                    Set set = getKeysByValue(hashMap, opponent);
                    Player player1 = (Player) set.iterator().next();
                    vm.put("activeColor", opponent.getColor());
                    vm.put("currentPlayer", opponent);
                    vm.put("redPlayer", player1);
                    vm.put("whitePlayer", opponent);
                } else {
                    Player player2 = playerLobby.getinGameMap().get(player);
                    vm.put("activeColor", player.getColor());
                    vm.put("currentPlayer", player);
                    vm.put("redPlayer", player);
                    vm.put("whitePlayer", player2);
                }
            }
            vm.put("activeColor", Piece.color.RED);
            Board board;
            if (session.attribute("board") == null){
                board = new Board();
                session.attribute("board", board);
            }
            else{
                board = session.attribute("board");
            }
            if (((Player) session.attribute("player")).getColor().equals(Piece.color.RED)){
                vm.put("board", board);
            }
            else{
                vm.put("board", board.reverse());
            }
        }
        else{
            return error("You must be signed in to play", request, response);
        }
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}
