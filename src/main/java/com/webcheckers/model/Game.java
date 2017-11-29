package com.webcheckers.model;


import java.util.Optional;

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
    Player loser;
    boolean gameOver;

    /**
     * Create a new game between two players.
     * @param player1
     * @param player2
     */
    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        player1Color = Piece.color.RED;
        player2Color = Piece.color.WHITE;
        this.activePlayer = player1;
        this.activeColor = Piece.color.RED;
        gameOver = false;
        BoardMaker boardMaker = new BoardMaker();
        if(player1.getName().toLowerCase().equals("redwin")){
            board = boardMaker.redWinsBoard();
        } else if(player1.getName().toLowerCase().equals("whitewin")){
            board = boardMaker.whiteWinsBoard();
        } else if(player1.getName().toLowerCase().equals("crownme")){
            board = boardMaker.makeKingBoard();
        } else if(player1.getName().toLowerCase().equals("kingjump")){
            board = boardMaker.kingJumpBoard();
        } else if(player1.getName().toLowerCase().equals("chainjump")){
            board = boardMaker.chainJumpBoard();
        }
    }

    /**
     * Getter for the first player.
     * @return
     */
    public Player getPlayer1() {
        return player1;
    }


    /**
     * Getter for the second player.
     * @return
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Check if the game is over or not
     * @return
     */
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(){
        this.gameOver = true;
    }

    /**
     * Start a new turn
     * Precondition: There is no turn currently active. The game is in-between turns.
     */
    public void startTurn(){
        if (this.turn == null){
            this.board.startTurn();
            this.turn = new Turn(activePlayer, activeColor, this.board);
            return;
        }
        throw new IllegalStateException("Illegal State. Game trying to start a turn before previous turn ended");
    }

    /**
     * End a player's turn. Set the active player and the active color to the correct values.
     */
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

    /**
     * Make a move. Just calls the makeMove on the turn object.
     * @param move
     */
    public void makeMove(Move move){
        if (this.turn != null){
            this.turn.makeMove(move);
            return;
        }
        throw new IllegalStateException("Attempted move when not player's turn");
    }

    /**
     * Undo a move. Just calls the makeMove on the turn object.
     */
    public void undo(){
        if (this.turn != null){
            this.turn.undo();
            return;
        }
        throw new IllegalStateException("Exception. Trying to Undo a move when not one's turn.");

    }

    /**
     * Remove player 1 from the game.
     */
    public void RemovePlayer1() {

        this.player1 = new Player("\"");
    }

    /**
     * Remove player 2 from the game
     */
    public void RemovePlayer2() {
        this.player2 = new Player("\"");
    }

    /**
     * Allow the board to be accessed elsewhere.
     * @return
     */
    public Board getBoard() {
        return turn.getBoard();
    }

    /**
     * Check if it is a player's turn
     * @param player who's turn is being checked
     * @return
     */
    public boolean checkTurn(Player player) {
        return this.activePlayer.equals(player);
    }

    public Move.moveType isValidMove(Move move){
        return this.turn.isValidMove(move);
    }

    /**
     * Check that a game is not over. Just call game over of the board object.
     * @return
     */
    public boolean checkGameOver(){
        Board tempBoard = turn.getBoard();
        if(tempBoard.checkGameOver(activeColor)){
            loser = activePlayer;
            gameOver = true;
            return true;
        }
        gameOver = false;
        return false;
    }

    public Player getWinner(){
        if (loser != null){
            if(loser.equals(player1)){
                return player2;
            } else {
                return player1;
            }
        }
        throw new IllegalStateException("No winner declared yet.");
    }

    @Override
    public String toString() {
        return "Game{" +
                player1.getName() +
                " vs." + player2.getName() +
                '}';
    }
}
