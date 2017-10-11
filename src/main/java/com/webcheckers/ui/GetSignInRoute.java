package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetSignInRoute implements Route {
	private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());
	private final TemplateEngine templateEngine;

	static final String SIGN_IN_MESSAGE_ATTR = "signInMessage";
	static final String SIGN_IN_MESSAGE = "PLease Enter a username: ";


	/**
	 * Create the Spark Route (UI controller) for the
	 * {@code GET /} HTTP request.
	 *
	 * @param templateEngine the HTML template rendering engine
	 */
	public GetSignInRoute(TemplateEngine templateEngine) {
		//Validation
		Objects.requireNonNull(templateEngine, "template engine cannot be empty");
		this.templateEngine = templateEngine;
		LOG.config("GetSignInRoute is initialized.");
	}


	/**
	 * Render the WebCheckers SignIn page.
	 *
	 * @param request  the HTTP request
	 * @param response the HTTP response
	 * @return the rendered HTML for the SignIn page
	 */
	@Override
	public Object handle(Request request, Response response) throws Exception {
		LOG.config("GetSignInRoute is invoked.");
		Map<String, Object> vm = new HashMap<>();
		vm.put(SIGN_IN_MESSAGE_ATTR, SIGN_IN_MESSAGE);
		return templateEngine.render(new ModelAndView(vm, "signIn.ftl"));
	}
}
