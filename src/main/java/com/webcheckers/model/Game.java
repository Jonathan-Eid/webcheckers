package com.webcheckers.model;


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
     * @param player1
     */
    public void RemovePlayer1(Player player1) {
        this.player1 = null;
    }

    /**
     * Remove player 2 from the game
     * @param player2
     */
    public void RemovePlayer2(Player player2) {
        this.player2 = null;
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
        return this.board.isValidMove(move);
    }

    /**
     * Check that a game is not over. Just call game over of the board object.
     * @return
     */
    public boolean checkGameOver(){
        Board tempBoard;
        if(activeColor.equals(Piece.color.RED)){
            tempBoard = board;
        } else {
            tempBoard = board.reverse();
        }
        return tempBoard.checkGameOver(activeColor);
    }

    @Override
    public String toString() {
        return "Game{" +
                player1.getName() +
                " vs." + player2.getName() +
                '}';
    }
}
