package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private static final String PLAYER_NAME_ATTR = "playerName";

    private final TemplateEngine templateEngine;

    static final String TITLE_ATTR = "title";
    static final String TITLE_VAL = "Welcome!";
    static final String NUM_PLAYERS_ATTR = "numPlayers";
    private PlayerLobby playerLobby;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
        // validation
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.config("GetHomeRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();
        vm.put(TITLE_ATTR, TITLE_VAL);
        if (session.attribute("player") == null){ //New user.
            vm.put(NUM_PLAYERS_ATTR, playerLobby.getNumPlayers());
        }
        else { //The player must be signed in.
            Player player = session.attribute("player");
            Objects.requireNonNull(player, "player must not be null");
            vm.put(PostSignInRoute.PLAYER_SIGNED_IN_ATTR, "YAYAYYAYAYA");
            vm.put(PLAYER_NAME_ATTR, player.getName());
            vm.put(PLAYER_LIST_ATTR, playerLobby.playerList(player.getName()));
        }
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }

}