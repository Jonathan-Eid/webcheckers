package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * Created by dis446 on 10/12/17.
 */
public class GetSignOutRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());

    static final String TITLE_ATTR = "title";
    static final String TITLE_VAL = "Welcome!";
    static final String NUM_PLAYERS_ATTR = "numPlayers";
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;

    public GetSignOutRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
        Objects.requireNonNull(templateEngine, "template engine cannot be null");
        Objects.requireNonNull(playerLobby, "player lobby cannot be null");
        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        LOG.config("GetSignOutRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.config("GetSignOutRoute handle called.");
        final Session session = request.session();
        Player p = session.attribute("player");
        session.attribute("player", null);
        playerLobby.signOutPlayer(p.getName());
        response.redirect(WebServer.HOME_URL); //Redirect to home page
        halt();
        return null;
    }
}
