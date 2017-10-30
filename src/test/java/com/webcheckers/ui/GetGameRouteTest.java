package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dis446 on 10/26/17.
 */
public class GetGameRouteTest {
    GetGameRoute CuT;

    Player player;
    PlayerLobby playerLobby;
    TemplateEngine engine;
    Response response;
    Session session;
    Request request;

    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();

        // create a unique CuT for each test
        CuT = new GetGameRoute(engine, playerLobby);
    }

    @Test
    public void error() throws Exception {

    }

    @Test
    public void handle() throws Exception {

    }

}