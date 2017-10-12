package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by dis446 on 10/12/17.
 */
public class GetSignOutRoute implements Route { // TODO
	private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());

	private TemplateEngine templateEngine;
	private PlayerLobby playerLobby;

	public GetSignOutRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
		Objects.requireNonNull(templateEngine, "template engine cannot be null");
		Objects.requireNonNull(playerLobby, "player lobby cannot be null");
		this.templateEngine = templateEngine;
		this.playerLobby = playerLobby;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		LOG.config("GetSignOutRoute handle called.");
		return null;
	}
}
