package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * This will remove the player from the system. This signs out the player from the game and will return them to the
 * original home page
 * Created by dis446, Ani, Andy, Johnny on 10/12/17.
 */
public class GetSignOutRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());

    private PlayerLobby playerLobby;

    /**
     * Constructor for GetSignOutRoute.
     * @param playerLobby the single playerLobby in the entire program.
     */
    public GetSignOutRoute(PlayerLobby playerLobby) {
        Objects.requireNonNull(playerLobby, "player lobby cannot be null");
        this.playerLobby = playerLobby;
        LOG.config("GetSignOutRoute is initialized.");
    }

    /**
     * Signs the player out and redirects them to the home page.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.config("GetSignOutRoute handle called.");
        final Session session = request.session();
        Player p = session.attribute("player");
        session.removeAttribute("player");
        playerLobby.signOutPlayer(p.getName());
        response.redirect(WebServer.HOME_URL); //Redirect to home page
        halt();
        return null;
    }
}
