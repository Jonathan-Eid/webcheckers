package com.webcheckers.ui;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;
import static com.webcheckers.ui.GetHomeRoute.NUM_PLAYERS_ATTR;
import static com.webcheckers.ui.GetHomeRoute.PLAYER_NAME_ATTR;
import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_ATTR;
import static com.webcheckers.ui.PostSignInRoute.USER_SIGNED_IN_ATTR;
import static spark.Spark.halt;

/**
 * Created by dis446 on 10/31/17.
 */
public class GetStartGameRoute implements Route {

    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    public GetStartGameRoute(final TemplateEngine engine, PlayerLobby playerLobby){
        Objects.requireNonNull(engine, "TemplateEngine must not be null");
        Objects.requireNonNull(playerLobby, "PlayerLobby must not be null");
        this.templateEngine = engine;
        this.playerLobby = playerLobby;
    }

    public String error(String error, Request request, Response response){
        final Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        Player player = session.attribute("player");
        vm.put("message", true);
        vm.put("error", error);
        vm.put(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE_VAL);
        vm.put(USER_SIGNED_IN_ATTR, true);
        vm.put(PLAYER_NAME_ATTR, player.getName());
        vm.put(NUM_PLAYERS_ATTR, playerLobby.getNumPlayers());
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        Game game;
        Session session = request.session();
        if (session.attribute(USER_SIGNED_IN_ATTR)){ //Check that player is logged in.
            Player player = session.attribute(USER_ATTR);
            if (!playerLobby.isInGame(player)){
                //Player is the user who started the game. They are player 1
                String opponentName = request.queryParams("opponent");
                Player opponent = playerLobby.getPlayer(opponentName);
                Objects.requireNonNull(player, "Player must not be null");
                if ((!(playerLobby.isInGame(player))) && (!(playerLobby.isInGame(opponent)))) {
                    //Mark the players as being in a game.
                    playerLobby.addToGame(player, opponent);
                    //Create the game object
                    game = new Game(player, opponent);
                    session.attribute(GAME_ATTR, game);
                    //Redirect to the game page.
                    response.redirect("/game");
                    halt();
                    return null;
                } else if (playerLobby.isInGame(player)) {
                    //Player is already in a game.
                    return error(player.getName() + " is already in a game", request, response);
                } else if (playerLobby.isInGame(opponent)) {
                    //Opponent is already in a game.
                    return error(opponent.getName() + " is already in a game", request, response);
                }
            }
            else{
                //Player is the user who was dragged into the game. They are player 2.
                Player opponent = playerLobby.getPlayerOpponent(player);
                Objects.requireNonNull(player, "Player must not be null");
                session.attribute(GAME_ATTR, new Game(opponent, player));
                response.redirect("/game");
                halt();
                return null;
            }
        }
        return null;
    }
}
