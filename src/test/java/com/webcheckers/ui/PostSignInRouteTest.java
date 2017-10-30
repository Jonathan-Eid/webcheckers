package com.webcheckers.ui;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;

import spark.*;

/**
 * Created by dis446 on 10/26/17.
 */
public class PostSignInRouteTest {

    private static final String INVALID_NAME = " \"errr\" ";
    private static final String ALREADY_IN = "Charlie";
    private static final String NEW_USER = "Paul";

    private PostSignInRoute CuT;

    //friendly objects
    private PlayerLobby playerLobby;

    // attributes holding mock objects
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;


    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();
        playerLobby.signInPlayer(ALREADY_IN);

        CuT = new PostSignInRoute(engine,playerLobby);

    }
    @Test
    public void invalidInput() throws Exception {
        when(request.queryParams("username")).thenReturn(INVALID_NAME);

        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request,response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof  Map);

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(vm.get(PostSignInRoute.INVALID_SIGN_IN_ATTR),"ERROR. Invalid name.");


    }

    @Test
    public void samePlayer() throws Exception {
        when(request.queryParams("username")).thenReturn(ALREADY_IN);

        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        CuT.handle(request,response);

        final Object model = myModelAndView.model;
        assertNotNull(model);
        assertTrue(model instanceof  Map);

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(vm.get(PostSignInRoute.INVALID_SIGN_IN_ATTR),"Player name " + ALREADY_IN + " is already in use");


    }

    @Test
    public void validInput() throws Exception {
        when(request.queryParams("username")).thenReturn(NEW_USER);

        final MyModelAndView myModelAndView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelAndView));

        // Invoke the test
        try {
            CuT.handle(request, response);
            fail("Redirects invoke halt excpetions.");
        } catch (HaltException e) {
            // expected
        }


        verify(response).redirect(WebServer.HOME_URL);


    }

}