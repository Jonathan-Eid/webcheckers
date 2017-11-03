package com.webcheckers.model;

import java.util.Stack;

/**
 * Created by Juna on 10/31/2017.
 */
public class Turn {

    /**
     * The Player who's turn it is.
     */
    Player player;

    /**
     * The Color of the player who's turn it is.
     */
    Piece.color color;

    /**
     * The stack of boards. Doing it this way allows moves to be "unmade" by popping of the stack and a move to be made
     * by pushing onto the stack.
     */
    Stack<Board> undoBoards;


    /**
     * Set up the turn
     * @param player
     * @param color
     * @param initialBoard
     */
    public Turn(Player player, Piece.color color, Board initialBoard){
        this.player=player;
        this.color = color;
        undoBoards = new Stack<>();
        undoBoards.push(initialBoard);
    }

    /**
     * Getter for the player
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for the color of the player
     * @return
     */
    public Piece.color getColor() { return color; }

    /**
     * Make a move. Create a copy of the board of the top of the stack, make the move on that copy and then push that
     * copy to the top of the stack
     * @param move The move to be made
     */
    public void makeMove(Move move) throws IllegalStateException{
        if (!undoBoards.peek().isValidMove(move).equals(Move.moveType.INVALID)){
            Board newBoard = new Board(undoBoards.peek());
            newBoard.makeMove(move);
            undoBoards.push(newBoard);
            return;
        }
        throw new IllegalStateException("Invalid Move");
    }

    /**
     * Undo a move by popping of the top of the stack after checking that the stack has at least one board in it.
     */
    public void undo() throws IllegalStateException{
        if (undoBoards.size() > 1){
            undoBoards.pop();
            return;
        }
        throw new IllegalStateException("No more moves to undo");
    }

    /**
     * This method is called when the turn is over and the board needs to be finalized.
     */
    public Board getBoard(){
        return undoBoards.peek();
    }

}
