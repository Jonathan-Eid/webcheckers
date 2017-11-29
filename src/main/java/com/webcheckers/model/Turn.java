package com.webcheckers.model;

import java.util.Stack;

/**
 * Created by Juna on 10/31/2017.
 */
public class Turn {

    /**
     * The Player whose turn it is.
     */
    Player player;

    /**
     * The Color of the player whose turn it is.
     */
    Piece.color color;

    /**
     * The stack of boards. Each board represents a move made during this turn. Doing it this way allows moves to
     * be "unmade" by popping of the stack and a move to be made by pushing onto the stack.
     */
    Stack<Board> undoBoards;


    /**
     * Set up the turn
     * @param player the player object who is allowed to make moves this turn
     * @param color the color of pieces the active player controls
     * @param initialBoard the single board object that represents the state of the board at the end of the previous turn
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
        if (!undoBoards.peek().isValidMove(move).equals(Move.moveType.INVALID)){    //is move valid on the top board of undoBoards?
            Board newBoard = new Board(undoBoards.peek());                          //make the move by adding a new board to the stack
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

    public Move.moveType isValidMove(Move move) {
        return undoBoards.peek().isValidMove(move);
    }
}
