package com.webcheckers.appl;

import com.webcheckers.model.Piece;
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

    private static List<Player> playerList;
    private static Map<Player, Player> inGameMap;
    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    public PlayerLobby() {
        playerList = new ArrayList<>();
        inGameMap = new HashMap<>();
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
            Player playerLogin = new Player(name);
            playerList.add(playerLogin);
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
            playerList.remove(new Player(name));
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
     * @return String
     */
    public String getNumPlayers() {
        return Integer.toString(playerList.size());
    }

    /**
     * Check that a player is signed in.
     * @param name String
     * @return boolean
     */
    private boolean isLoggedIn(String name) {
        return playerList.contains(new Player(name));
    }

    public Piece.color getColor(Player player){
        for (Player player1 : playerList){
            if (player.equals(player)){
                return player.getColor();
            }
        }
        return null;
    }

    /**
     * List out the Players so that the HomePage can display them.
     * @param name String
     * @return String
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
                result = result.concat("<form action=\"/game\" method=\"GET\"> <input type=\"hidden\" id=\"name\" name=\"opponent\" value=\"" +
                        player.getName() + "\"> <button type=\"submit\" >" + player.getName() + "</button> </div> </form>");
            }
        }
        LOG.config(result);
        return result;
        //<button name="subject" type="submit" value="HTML">HTML</button>
    }

    /**
     * report whether a player is already in a game
     * @param player a player who has signed in, and may or may not be in a game
     * @return true if the player is in a game right now, false otherwise
     */
    public boolean isInGame(Player player){
        return inGameMap.keySet().contains(player) || inGameMap.values().contains(player);
    }

    /**
     * adds a player to the list of players in games
     * @param player Player
     */
    public void addToGame(Player player, Player player1){
        inGameMap.put(player, player1);
    }

    /**
     * removes a player from the lsit of players in games
     * @param player Player
     */
    public void removeFromGame(Player player){
        inGameMap.remove(player);
    }

    public Map<Player, Player> getinGameMap(){
        return inGameMap;
    }

}
