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

    Map <String, Player> playerList;
    List<Game> GameList;
    public enum SignInResult {SIGNED_IN, INVALID_INPUT, INVALID_PLAYER, SIGNED_OUT}

    public PlayerLobby() {
        playerList = new HashMap<>();
        GameList  = new ArrayList<>();
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
            playerList.put(name, new Player(name));
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
            playerList.remove(name);
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
                return playerList.get(name);
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
        return playerList.containsKey(name);
    }

    /**public Piece.color getColor(String name) throws IllegalStateException{
        Player player = getPlayer(name);
        for (Player player1 : playerList){
            if (player1.equals(player)){
                return player.getColor();
            }
        }
        throw new IllegalStateException("Invalid player color lookup");
    }*/

    /**
     * List out the Players so that the HomePage can display them.
     * @param name String
     * @return String
     */
    public String playerList(String name){
        String result = "";
        for (Player player : playerList){
            if (!player.getName().equals(name)) {
                result = result.concat("<form action=\"/game\" method=\"GET\"> <input type=\"hidden\" id=\"name\" " +
                        "name=\"opponent\" value=\"" + player.getName() + "\"> <button type=\"submit\" >" +
                        player.getName() + "</button> </div> </form>");
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
        for (Game game: GameList) {
            if (game.getPlayer1() == player || game.getPlayer2() == player){
                return true;
            }

        }
        return false;
    }

    /**
     * adds a player to the list of players in games
     * @param player Player
     */
    public void addToGame(Player player, Player player1){
        GameList.add(new Game(player,player1));
    }

    public Player getPlayerOpponent(Player player){
        for (Game game: GameList) {
            if (game.getPlayer1() == player){
                return game.getPlayer2();
            }
            else if (game.getPlayer2() == player){
                return game.getPlayer2();
            }
        }
        return null;
    }

    /**
     * removes a player from the map of players in games
     * @param player Player
     */
    public void removeFromGame(Player player){
        for (Game game: GameList) {
            if (game.getPlayer1() == player){
                game.RemovePlayer1(player);
            }
            else if (game.getPlayer2() == player){
                game.RemovePlayer2(player);
            }
        }
    }

    public List<Game> getinGameList(){
        return GameList;
    }

}
