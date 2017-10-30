package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dis446 on 10/26/17.
 */
public class GetSignOutRouteTest {

    private GetSignOutRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // create a unique CuT for each test
        CuT = new GetSignOutRoute(playerLobby);
    }
    @Test (expected = HaltException.class)
    public void handle() throws Exception {
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));
        // Invoke the test (ignore the output)
        CuT.handle(request, response);
    }
}