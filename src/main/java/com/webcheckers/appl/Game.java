package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.model.Turn;

/**
 * Created by Juna on 10/31/2017.
 */
public class Game {

    private Player player1;
    private Player player2;
    private Piece.color player1Color;
    private Piece.color player2Color;
    private int turnCount;
    private Board board;
    public enum turn {Player_1, Player_2}
    private turn playerTurn;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        turnCount = 0;
        player1Color = Piece.color.RED;
        player2Color = Piece.color.WHITE;
        playerTurn = turn.Player_1;

    }

    public int getTurnCount() {
        return turnCount;
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

    public Board getBoard() {
        return board;
    }

    public boolean checkTurn(Player player) {
        if (playerTurn == turn.Player_1){
            return player.equals(player1);
        }
        else{
            return player.equals(player2);
        }
    }
}
