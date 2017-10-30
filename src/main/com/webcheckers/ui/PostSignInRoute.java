package webcheckers.ui;

import webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static spark.Spark.halt;

/**
 * This dictates the action to take once the player has tried to sign in. If the player enters a valid username then
 * it will save the player and return the home page where the player can see other players signed on
 * and choose someone to play with
 * Created by Juna, Disney, Andy, and Ani on 10/12/2017.
 */

public class PostSignInRoute implements Route {
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    static final String INVALID_SIGN_IN_ATTR = "invalidSignInMessage";
    static final String PLAYER_SIGNED_IN_ATTR = "playerSignedIn";
    static final String PLAYER_LIST_ATTR = "playerList";


    public PostSignInRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "template engine cannot be empty");
        Objects.requireNonNull(playerLobby, "playerlobby cannot be empty");
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }


    /**
     * Handles the sign in of the player. Validates the input and either renders the home page or redirects to the sign
     * in page if the sign in failed.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {

        final Map<String, Object> vm = new HashMap<>();
        final String userName = request.queryParams("username");
        switch (playerLobby.signInPlayer(userName)) {
            case SIGNED_IN:
                final Session session = request.session();
                //Store the Player Object into the session.
                session.attribute("player", playerLobby.getPlayer(userName));
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            case INVALID_INPUT:
                vm.put(GetSignInRoute.SIGN_IN_MESSAGE_ATTR, GetSignInRoute.SIGN_IN_MESSAGE);
                vm.put(INVALID_SIGN_IN_ATTR, "ERROR. Invalid name.");
                return templateEngine.render(new ModelAndView(vm, "signIn.ftl"));
            case INVALID_PLAYER:
                vm.put(GetSignInRoute.SIGN_IN_MESSAGE_ATTR, GetSignInRoute.SIGN_IN_MESSAGE);
                vm.put(INVALID_SIGN_IN_ATTR, "Player name " + userName + " is already in use");
                return templateEngine.render(new ModelAndView(vm, "signIn.ftl"));
            case SIGNED_OUT:
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            default:
                throw new NoSuchElementException("Invalid result of sign in received");
        }
    }
}
