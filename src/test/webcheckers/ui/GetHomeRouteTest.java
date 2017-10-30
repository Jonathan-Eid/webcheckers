package webcheckers.ui;

import webcheckers.appl.PlayerLobby;
import org.junit.Before;
import org.junit.Test;
import spark.*;

import java.util.Map;

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
    private GetHomeRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;


    @Before
    public void setUp() throws Exception {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);

        // create a unique CuT for each test
        CuT = new GetHomeRoute(engine, playerLobby);
    }


    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        /*
        when(session.isNew()).thenReturn(true);
        final MyModelAndView myModelView = new MyModelAndView();
        when(engine.render(any(ModelAndView.class))).thenAnswer(MyModelAndView.makeAnswer(myModelView));

        CuT.handle(request, response);
        final Object model = myModelView.model;
        assertNotNull(model);
        assertTrue(model instanceof Map);

        final Map<String, Object> vm = (Map<String, Object>) model;
        assertEquals(GetHomeRoute.TITLE_ATTR, vm.get(GetHomeRoute.TITLE_VAL));
        assertEquals(Boolean.FALSE, vm.get(GetHomeRoute.PLAYER_NAME_ATTR));
        */
    }

}