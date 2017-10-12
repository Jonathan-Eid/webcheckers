package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
	private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

	private final TemplateEngine templateEngine;

	static final String TITLE_ATTR = "title";
	static final String TITLE_VAL = "Welcome!";
	static final String NUM_PLAYERS_ATTR = "numPlayers";

	/**
	 * Create the Spark Route (UI controller) for the
	 * {@code GET /} HTTP request.
	 *
	 * @param templateEngine the HTML template rendering engine
	 */
	public GetHomeRoute(final TemplateEngine templateEngine) {
		// validation
		Objects.requireNonNull(templateEngine, "templateEngine must not be null");
		//
		this.templateEngine = templateEngine;
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
		LOG.finer("GetHomeRoute is invoked.");
		final Session httpSession = request.session();
		Map<String, Object> vm = new HashMap<>();
		vm.put(TITLE_ATTR, TITLE_VAL);
		//vm.put(NUM_PLAYERS_ATTR, ); //TODO Display number of players
		return templateEngine.render(new ModelAndView(vm, "home.ftl"));
	}

}