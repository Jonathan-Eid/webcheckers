package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import freemarker.ext.util.IdentityHashMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Juna on 10/12/2017.
 */
public class PostSignInRoute implements Route {

    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    public PostSignInRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "template engine cannot be empty");
        this.templateEngine = templateEngine;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String,Object> vm = new HashMap<>();

        return null;
    }
}
