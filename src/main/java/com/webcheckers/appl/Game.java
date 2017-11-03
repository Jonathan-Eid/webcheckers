package com.webcheckers.appl;

import com.webcheckers.model.*;

/**
 * Created by Juna on 10/31/2017.
 */
public class Game {

    Player activePlayer;
    Player player1;
    Player player2;
    Piece.color  activeColor;
    Piece.color player1Color;
    Piece.color player2Color;
    Board board;
    Turn turn;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        player1Color = Piece.color.RED;
        player2Color = Piece.color.WHITE;
        this.activePlayer = player1;
        this.activeColor = Piece.color.RED;
    }

    public Player getPlayer1() {
        return player1;

    }

    public void startTurn(){
       Piece.color color;
        if (this.turn == null){
            this.turn = new Turn(activePlayer, activeColor, this.board);
            return;
        }
        throw new IllegalStateException("Illegal State. Game trying to start a turn before previous turn ended");
    }

    public void finishTurn(){
        this.board = this.turn.getBoard();
        if (this.player1.equals(activePlayer)){
            this.activePlayer = player2;
            activeColor = Piece.color.WHITE;
        }
        else{
            this.activePlayer = player1;
            this.activeColor = Piece.color.RED;
        }
        this.turn = null;
    }

    public void makeMove(Move move){
        if (this.turn != null){
            this.turn.makeMove(move);
            return;
        }
        throw new IllegalStateException("Attempted move when not player's turn");
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
        return this.activePlayer.equals(player);
    }
}
