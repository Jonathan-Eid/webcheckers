package webcheckers.appl;

import webcheckers.appl.PlayerLobby;
import webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dis446 on 10/26/17.
 */

/**
 * The unit test suite for PlayerLobby
 */
public class PlayerLobbyTest {
    PlayerLobby CuT;

    @Before
    public void setUp() throws Exception {
        CuT = new PlayerLobby();
    }

    @Test
    public void signInPlayer(){
        assertEquals("Player sign in must return SignInResult.SIGNED_IN", PlayerLobby.SignInResult.SIGNED_IN, CuT.signInPlayer("test"));
        assertNotNull(CuT.getPlayer("test"));
        assertEquals(PlayerLobby.SignInResult.INVALID_INPUT, CuT.signInPlayer(""));
        assertEquals(PlayerLobby.SignInResult.INVALID_INPUT, CuT.signInPlayer("\""));
        assertEquals(PlayerLobby.SignInResult.INVALID_PLAYER, CuT.signInPlayer("test"));
        assertSame(1, CuT.playerList.size());
    }


    @Test
    public void signOutPlayer() throws Exception {
        when(CuT.signInPlayer("test"));
    }

    @Test
    public void getPlayer() throws Exception {

    }

    @Test
    public void getNumPlayers() throws Exception {

    }

    @Test
    public void getColor() throws Exception {

    }

    @Test
    public void playerList() throws Exception {

    }

    @Test
    public void isInGame() throws Exception {

    }

    @Test
    public void addToGame() throws Exception {

    }

    @Test
    public void removeFromGame() throws Exception {

    }

    @Test
    public void getinGameMap() throws Exception {

    }

}