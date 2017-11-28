package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameCenter {

    List<Game> gameList;

    public GameCenter(){
        this.gameList = new ArrayList<>();
    }

    /**
     * Create a new Game object and associate it with two players.
     * @param player1
     * @param player2
     * @return
     */
    public Game newGame(Player player1, Player player2){
        Game newGame = new Game(player1, player2);
        this.gameList.add(newGame);
        return newGame;
    }

    /**
     * report whether a player is already in a game
     * @param player a player who has signed in, and may or may not be in a game
     * @return true if the player is in a game right now, false otherwise
     */
    public boolean isInGame(Player player){
        for (Game game: gameList) {
            if (game.getPlayer1().equals(player) || game.getPlayer2().equals(player)){
                return true;
            }
        }
        return false;
    }

    /**
     * Given a player, find out which game they are currently in,
     * @param player
     * @return
     */
    public Game getGameFromPlayer(Player player){
        for (Game game: gameList) {
            if (game.getPlayer2().equals(player) || game.getPlayer1().equals(player)){
                return game;
            }
        }
        throw new IllegalStateException("Get Game from Player called when player not in a game");
    }

    public void removeGame(Player player){
        int i = 0;
        for (Game game: gameList){
            if(game.getPlayer1().equals(player) || game.getPlayer2().equals(player)){
                break;
            }
            i++;
        }
        gameList.remove(i);
    }
}
