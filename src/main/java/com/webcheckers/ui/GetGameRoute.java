package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

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
    public GetGameRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
    }

    public Object handle(Request request, Response response) {
        LOG.config("GetGameRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        //TODO make the board
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }

}
