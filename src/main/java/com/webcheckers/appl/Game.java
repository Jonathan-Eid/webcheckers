package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.model.Turn;

/**
 * Created by Juna on 10/31/2017.
 */
public class Game {

    private Player player1;
    private Player player2;

    private static int turnCount = 0;
    private Board board;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void RemovePlayer1(Player player1) {
        this.player1 = null;
    }

    public void RemovePlayer2(Player player2) {
        this.player2 = null;
    }
}
