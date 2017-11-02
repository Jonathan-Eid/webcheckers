package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

import static com.webcheckers.ui.PostSignInRoute.PLAYER_LIST_ATTR;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dis446 on 10/26/17.
 */
public class GetHomeRouteTest {

    /**
     * The component-under-test (CuT).
     */
    private com.webcheckers.ui.GetHomeRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    public static final String PLAYER_NAME_ATTR = "playerName";


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
        CuT = new GetHomeRoute(engine, playerLobby);
    }


    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        when(session.isNew()).thenReturn(true);
        final com.webcheckers.ui.MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertNotNull(model);
        assertNotNull(engine);
        assertNotNull(playerLobby);
        assertTrue(model instanceof Map);
    }

    @Test
    public void signed_in() {
        Player player = new Player("name");
        when(session.attribute("player")).thenReturn(player);
        playerLobby.signInPlayer("name");

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request,response);
        final Object model = myModelView.model;
        final Map<String, Object> vm = (Map<String, Object>)model;

        assertNotNull(model);
        assertTrue(model instanceof Map);
        assertNotNull(engine);
        assertNotNull(playerLobby);
        assertEquals(playerLobby.getNumPlayers(), Integer.toString(1));
        assertNotNull(vm.get(PostSignInRoute.USER_SIGNED_IN_ATTR));
        assertTrue((boolean)vm.get(PostSignInRoute.USER_SIGNED_IN_ATTR));
        assertNotNull(vm.get(PLAYER_NAME_ATTR));
        assertNotNull(vm.get(PLAYER_LIST_ATTR));
        assertEquals(vm.get(PLAYER_LIST_ATTR), playerLobby.playerList(player.getName()));
    }

    @Test
    public void game_divert() {
        Player player = new Player("name");
        Player opponent = new Player("opponentName");
        when(session.attribute("player")).thenReturn(player);
        playerLobby.signInPlayer("name");
        playerLobby.signInPlayer("opponent");
        playerLobby.addToGame(player, opponent);
        playerLobby.addToGame(opponent, player);

        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));


        try {
            CuT.handle(request,response);
            final Object model = myModelView.model;
            final Map<String, Object> vm = (Map<String, Object>)model;

            assertNotNull(model);
            assertTrue(model instanceof Map);
            assertNotNull(engine);
            assertNotNull(playerLobby);
            assertEquals(playerLobby.getNumPlayers(), Integer.toString(2));
            assertNotNull(vm.get(PostSignInRoute.USER_SIGNED_IN_ATTR));
            assertTrue((boolean)vm.get(PostSignInRoute.USER_SIGNED_IN_ATTR));
            assertNotNull(vm.get(PLAYER_NAME_ATTR));
            assertNotNull(vm.get(PLAYER_LIST_ATTR));
            assertEquals(vm.get(PLAYER_LIST_ATTR), playerLobby.playerList(player.getName()));
        } catch (HaltException e) {
            // expected
        }

        verify(response).redirect(WebServer.GAME_URL);
    }

}