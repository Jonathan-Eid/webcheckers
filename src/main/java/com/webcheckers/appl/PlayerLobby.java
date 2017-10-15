package com.webcheckers.appl;

import com.webcheckers.model.Player;
import com.webcheckers.ui.WebServer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());

    private static List<Player> playerList;
    private static List<Player> playersInGamesList;
    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    public PlayerLobby() {
        playerList = new ArrayList<>();
        playersInGamesList = new ArrayList<>();
    }

    /**
     * Validates the name and signs the player in. Stores the player in the PlayerList
     * @param name
     * @return SignInResult Enum
     */
    public SignInResult signInPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            Player playerLogin = new Player(name);
            playerList.add(playerLogin);
            return SignInResult.SIGNED_IN;
        }
    }

    /**
     * Signs the player out, removing them from the PlayerList.
     * @param name
     * @return
     */
    public SignInResult signOutPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (!isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            playerList.remove(new Player(name));
            return SignInResult.SIGNED_OUT;
        }
    }

    /**
     * Validates the name. A name cannot be empty and cannot contain the (") character
     * @param name
     * @return
     */
    private boolean invalidInput(String name) {
        return name.contains("\"") || name.equals("");
    }

    /**
     * Fetches the Player Object from the PlayerList based on the name.
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        if (!invalidInput(name)) {
            if (isLoggedIn(name)) {
                for (Player player : playerList){
                    if (player.getName().equals(name)){
                        return player;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get's the total number of players signed in.
     * @return
     */
    public String getNumPlayers() {
        return Integer.toString(playerList.size());
    }

    /**
     * Check that a player is signed in.
     * @param name
     * @return
     */
    private boolean isLoggedIn(String name) {
        return playerList.contains(new Player(name));
    }

    /**
     * List out the Players so that the HomePage can display them.
     * @param name
     * @return
     */
    public String playerList(String name){
        /*
        <form action="./signingIn" method="POST">
              <label for="user">username:</label>
              <input type="text" id="user" name="username">
              <button type="submit">Sign In</button>
          </form>
        */
        String result = "";
        for (Player player : playerList){
            if (!player.getName().equals(name)) {
                result = result.concat("<form action=" + WebServer.GAME_URL +
                        " method=\"Get\" <label for=\"opponent\"></label><button type = \"submit\">" +
                        player.getName() + "</button> </form> <br/>");
            }
        }
        LOG.config(result);
        return result;
    }

    /**
     * report whether a player is already in a game
     * @param player a player who has signed in, and may or may not be in a game
     * @return true if the player is in a game right now, false otherwise
     */
    public boolean isInGame(Player player){
        return playersInGamesList.contains(player);
    }

    public void addToGame(Player player){
        playersInGamesList.add(player);
    }

    public void removeFromGame(Player player){
        playersInGamesList.remove(player);
    }

}
