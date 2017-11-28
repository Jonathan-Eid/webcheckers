package com.webcheckers.appl;

import com.sun.org.apache.regexp.internal.RE;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;


import javax.swing.text.DefaultEditorKit;

import java.util.HashMap;

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

    final String player1Name = "player1";
    final String player2Name = "player2";
    final String player3Name = "player3";
    Player player1;
    Player player2;
    Player player3;
    PlayerLobby CuT;
    GameCenter gameCenter;

    @Before
    public void setUp() throws Exception {
        CuT = new PlayerLobby(gameCenter);
        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);
        when(player1.getName()).thenReturn(player1Name);
        when(player2.getName()).thenReturn(player2Name);
        when(player3.getName()).thenReturn(player3Name);
    }

    @Test
    public void signInPlayer(){
        assertEquals("Player sign in must return SignInResult.SIGNED_IN", PlayerLobby.SignInResult.SIGNED_IN,
                CuT.signInPlayer(player1Name));
        assertNotNull("Player of name test must not be null", CuT.getPlayer(player1Name));
        assertEquals("An empty name should return an INVALID_INPUT", PlayerLobby.SignInResult.INVALID_INPUT,
                CuT.signInPlayer(""));
        assertEquals(PlayerLobby.SignInResult.INVALID_INPUT, CuT.signInPlayer("\""));

        //A player cannot sign in with a name already taken.
        assertEquals(PlayerLobby.SignInResult.INVALID_PLAYER, CuT.signInPlayer(player1Name));
        assertSame(1, CuT.playerMap.size());
    }


    @Test
    public void signOutPlayer() throws Exception {
        CuT.signInPlayer(player1Name);
        assertSame(1, CuT.playerMap.size());
        assertEquals(PlayerLobby.SignInResult.SIGNED_OUT, CuT.signOutPlayer(player1Name));
        assertSame(0, CuT.playerMap.size());
    }

    @Test
    public void getPlayer() throws Exception {
        CuT.signInPlayer(player1Name);
        assertNotNull(CuT.getPlayer(player1Name));
        //assertEquals("Player of name test should exist", player1, CuT.getPlayer(player1Name));
        assertNull(CuT.getPlayer(player2Name));
    }

    @Test
    public void getNumPlayers() throws Exception {
        assertEquals(Integer.toString(0), CuT.getNumPlayers());
        CuT.signInPlayer(player1Name);
        assertEquals(Integer.toString(1), CuT.getNumPlayers());
    }

    @Test
    public void playerList() throws Exception {
        CuT.signInPlayer(player1Name);
        CuT.signInPlayer(player2Name);
        assertEquals("<form action=\"/game\" method=\"GET\"> <input type=\"hidden\" id=\"name\" " +
                        "name=\"opponent\" value=\"" + player1Name + "\"> <button type=\"submit\" >" + player1Name +
                        "</button> </div> </form>",
                CuT.playerList(player2Name));
    }

    @Test
    public void isInGame() throws Exception {
        CuT.signInPlayer(player1Name);
        CuT.signInPlayer(player2Name);
        CuT.addToGame(player1, player2);
        //assertTrue(CuT.isInGame(player1));
        //assertFalse(CuT.isInGame(player3));
    }

    @Test
    public void addToGame() throws Exception {
        CuT.signInPlayer(player1Name);
        CuT.signInPlayer(player2Name);
        CuT.addToGame(player1,player2);
        HashMap<Player, Player> expectedMap = new HashMap<>();
        expectedMap.put(player1, player2);
        expectedMap.put(player2, player1);
        assertEquals(CuT.playerPlayerMap,expectedMap);
    }

    @Test
    public void removeFromGame() throws Exception {
        CuT.signInPlayer(player1Name);
        CuT.signInPlayer(player2Name);
        CuT.addToGame(player1, player2);
        //assertTrue(CuT.isInGame(player1));
        //assertTrue(CuT.isInGame(player2));
        //CuT.quitGame(player1);
        //assertFalse(CuT.isInGame(player1));
        //assertFalse(CuT.isInGame(player2));
    }
/*
    @Test
    public void getinGameMap() throws Exception {
        CuT.addToGame(player1, player2);
        HashMap<Player, Player> testMap = new HashMap<>();
        testMap.put(player1, player2);
        testMap.put(player2, player1);
        assertEquals(testMap, CuT.getinGameMap());
    }
*/
}