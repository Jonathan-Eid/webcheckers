package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by dis446 on 10/12/17.
 */
public class GetSignOutRoute implements Route { // TODO
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
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		LOG.config("GetSignOutRoute handle called.");
		final Session session = request.session();
		playerLobby.signOutPlayer(session.attribute("player"));
		Map<String, Object> vm = new HashMap<>();
		vm.put(TITLE_ATTR, TITLE_VAL);
		//vm.put(NUM_PLAYERS_ATTR, ); //TODO Display number of players
		return templateEngine.render(new ModelAndView(vm, "home.ftl"));
	}
}
