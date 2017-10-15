package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Andrew Didycz on 10/12/17.
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

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
        //TODO make the board
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }

}
