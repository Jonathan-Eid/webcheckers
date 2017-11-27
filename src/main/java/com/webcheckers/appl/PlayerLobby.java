package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Keeps track of all the players that are signed in. It's an information expert on signed in players :P
 * created by Disney, Andy, Ani, and Johnny
 */

public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());

    Map <String, Player> playerMap;
    Map<Player, Player> playerPlayerMap;
    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    /**
     * Initilize the playerMap, gameList and playerPlayerMap
     */
    public PlayerLobby() {
        playerMap = new HashMap<>();        //associates Strings (name) to Players
        playerPlayerMap = new HashMap<>();  //associates Players to other Players (their opponent)
    }

    /**
     * Validates the name and signs the player in. Stores the player in the PlayerList
     * @param name String
     * @return SignInResult Enum
     */
    public SignInResult signInPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            playerMap.put(name, new Player(name));
            return SignInResult.SIGNED_IN;
        }
    }

    /**
     * Signs the player out, removing them from the PlayerList.
     * @param name String
     * @return SignInResult
     */
    public SignInResult signOutPlayer(String name) {
        if (invalidInput(name)) {
            return SignInResult.INVALID_INPUT;
        } else if (!isLoggedIn(name)) {
            return SignInResult.INVALID_PLAYER;
        } else {
            playerMap.remove(name);
            return SignInResult.SIGNED_OUT;
        }
    }

    /**
     * Validates the name. A name cannot be empty and cannot contain the (") character
     * @param name String
     * @return boolean
     */
    private boolean invalidInput(String name) {
        return name.contains("\"") || name.equals("");
    }

    /**
     * Fetches the Player Object from the PlayerList based on the name.
     * @param name String
     * @return Player
     */
    public Player getPlayer(String name) {
        if (!invalidInput(name)) {
            if (isLoggedIn(name)) {
                return playerMap.get(name);
            }
        }
        return null;
    }

    /**
     * Get's the total number of players signed in.
     * @return String
     */
    public String getNumPlayers() {
        return Integer.toString(playerMap.size());
    }

    /**
     * Check that a player is signed in.
     * @param name String
     * @return boolean
     */
    private boolean isLoggedIn(String name) {
        return playerMap.containsKey(name);
    }

    /**
     * List out the Players so that the HomePage can display them.
     * @param name String
     * @return String
     */
    public String playerList(String name){
        String result = "";
        for (String playerName : playerMap.keySet()){
            Player player = playerMap.get(playerName);
            if (!playerName.equals(name)) {
                result = result.concat("<form action=\"/startGame\" method=\"GET\"> <input type=\"hidden\" id=\"name\" " +
                        "name=\"opponent\" value=\"" + player.getName() + "\"> <button type=\"submit\" >" +
                        player.getName() + "</button> </div> </form>");
            }
        }
        return result;
    }


    /**
     * adds a player to the list of players in games
     * @param player Player
     */
    public void addToGame(Player player, Player player1){
        playerPlayerMap.put(player, player1);
        playerPlayerMap.put(player1, player);
    }

    /***
     * Fetch the player that is the opponent of a given player.
     * @param player Player whose opponent is wanted.
     * @return
     */
    public Player getPlayerOpponent(Player player){
        return playerPlayerMap.get(player);
    }

    /**
     * removes a player from the map of players in games
     * @param player Player
     */
    public void removeFromGame(Player player){
        Player second = playerPlayerMap.get(player);
        playerPlayerMap.remove(player);
        playerPlayerMap.remove(second);
    }
}
