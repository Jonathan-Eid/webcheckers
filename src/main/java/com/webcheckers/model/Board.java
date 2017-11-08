package com.webcheckers.model;

import javafx.geometry.Pos;

import java.util.Iterator;
import java.util.Stack;

/**
 *creates a template for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Board implements Iterable{

    private Row[] rows;
    public enum state{NO_MOVE, SINGLE_MOVE, CAPTURE_MOVE};
    private state boardState;
    private Position lastCapture;


    /**
     * standard constructor
     * Initializes the board with Rows containing spaces that contain pieces
     */
    public Board(){
        this.boardState = state.NO_MOVE;
        this.rows = new Row[8];
        for (int y = 0; y < 8; y++){
            Row row = new Row(y);
            rows[y] = row;
            for (int x = 0; x < 8; x++){
                if (y < 3){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.color.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.WHITE));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.WHITE));
                        }
                        else{
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                    }
                }
                else if (y > 4){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.color.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.RED));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.RED));
                        }
                        else{
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                    }
                }
                else{
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.color.RED);
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.color.RED);
                        }
                        else{
                            rows[y].setSpace(x, Square.color.EMPTY_WHITE);
                        }
                    }
                }
            }
        }
    }

    /**
     * copy constructor, creates a deep copy of board
     * @param other the board to copy
     */
    public Board(Board other){
        this.boardState = other.boardState;
        this.rows = new Row[other.rows.length];
        for (int i = 0; i < this.rows.length; i++){
            this.rows[i] = new Row(other.rows[i]);
        }
    }

    /**
     * determine if a move is valid, and whether it is a single or capture move
     * @param move a move that may or may not be valid
     * @return the type of the move that was passed in (INVALID, SINGLE, CAPTURE)
     */
    public Move.moveType isValidMove(Move move){

        int startX = move.getStart().getCell();
        int startY = move.getStart().getRow();
        int endX = move.getEnd().getCell();
        int endY = move.getEnd().getRow();
        int deltaX = endX - startX;
        int deltaY = endY - startY;
        Piece startPiece = rows[startY].getSpace(startX).getPiece();


        switch (boardState) {
            case NO_MOVE:
                if (!rows[endY].getSpace(endX).hasPiece()) {
                    if (startPiece.getColor().equals(Piece.color.WHITE)) {
                        if(Math.abs(deltaX) == 1 && (deltaY == 1 || (deltaY == -1 && startPiece.getType() == Piece.pieceType.KING))){
                            return Move.moveType.SINGLE;
                        } else if (Math.abs(deltaX) == 2 && (deltaY == 2 || (deltaY == -2 && startPiece.getType() == Piece.pieceType.KING))){
                            Piece jumpedPiece = rows[startY + deltaY/2].getSpace(startX + deltaX/2).getPiece();
                            if(jumpedPiece instanceof Piece){
                                if(jumpedPiece.getColor() != startPiece.getColor()) {
                                    return Move.moveType.CAPTURE;
                                } else {
                                    return Move.moveType.INVALID;
                                }
                            } else {
                                return Move.moveType.INVALID;
                            }
                        } else {
                            return Move.moveType.INVALID;
                        }
                    } else if (startPiece.getColor().equals(Piece.color.RED)) {
                        if(Math.abs(deltaX) == 1 && (deltaY == -1 || (deltaY == 1 && startPiece.getType() == Piece.pieceType.KING))){
                            return Move.moveType.SINGLE;
                        } else if (Math.abs(deltaX) == 2 && (deltaY == -2 || (deltaY == -2 && startPiece.getType() == Piece.pieceType.KING))){
                            Piece jumpedPiece = rows[startY + deltaY/2].getSpace(startX + deltaX/2).getPiece();
                            if(jumpedPiece instanceof Piece){
                                if(jumpedPiece.getColor() != startPiece.getColor()) {
                                    return Move.moveType.CAPTURE;
                                } else {
                                    return Move.moveType.INVALID;
                                }
                            } else {
                                return Move.moveType.INVALID;
                            }
                        } else {
                            return Move.moveType.INVALID;
                        }
                    }
                }
                return Move.moveType.INVALID;
            case SINGLE_MOVE:
                return Move.moveType.INVALID;
            case CAPTURE_MOVE:
                if(move.getStart().equals(lastCapture)){
                    if (startPiece.getColor().equals(Piece.color.WHITE)) {
                        if (Math.abs(deltaX) == 2 && (deltaY == 2 || (deltaY == -2 && startPiece.getType() == Piece.pieceType.KING))) {
                            Piece jumpedPiece = rows[startY + deltaY / 2].getSpace(startX + deltaX / 2).getPiece();
                            if (jumpedPiece instanceof Piece) {
                                if (jumpedPiece.getColor() != startPiece.getColor()) {
                                    return Move.moveType.CAPTURE;
                                } else {
                                    return Move.moveType.INVALID;
                                }
                            } else {
                                return Move.moveType.INVALID;
                            }
                        } else {
                            return Move.moveType.INVALID;
                        }
                    } else if (startPiece.getColor().equals(Piece.color.RED)) {
                        if (Math.abs(deltaX) == 2 && (deltaY == -2 || (deltaY == -2 && startPiece.getType() == Piece.pieceType.KING))) {
                            Piece jumpedPiece = rows[startY + deltaY / 2].getSpace(startX + deltaX / 2).getPiece();
                            if (jumpedPiece instanceof Piece) {
                                if (jumpedPiece.getColor() != startPiece.getColor()) {
                                    return Move.moveType.CAPTURE;
                                } else {
                                    return Move.moveType.INVALID;
                                }
                            } else {
                                return Move.moveType.INVALID;
                            }
                        } else {
                            return Move.moveType.INVALID;
                        }
                    }
                }
                break;
            default:
                throw new IllegalStateException("the move was null");
        }
        return Move.moveType.INVALID;
    }

    /**
     * takes a move object and make a change to the board, assuming the move is valid
     * @param move any validated Move object
     */
    public void makeMove(Move move){
        if (move.type == null){
            throw new IllegalStateException("Move has not moveType");
        }
        Position start = move.getStart();
        Position end = move.getEnd();
        Square startSquare = rows[start.getRow()].getSpace(start.getCell());
        Square endSquare = rows[end.getRow()].getSpace(end.getCell());
        endSquare.setPiece(startSquare.getPiece());
        startSquare.setPiece(null);
        if (move.type.equals(Move.moveType.CAPTURE)) {
            int y = (end.getRow() + start.getRow()) / 2;
            int x = (end.getCell() + start.getCell()) / 2;
            Square middleSquare = this.rows[y].getSpace(x);
            middleSquare.setPiece(null);
            this.lastCapture = end;
        }
    }

    /**
     * create a version of the board rotated 180 degrees
     * this rotated board represents the board a seen by the other player in a checkers game
     * @return a board that has had all of its rows and cells reversed
     */
    public Board reverse(){
        Board board = new Board();
        Stack<Row> rowStack = new Stack<>();
        int i = 0;
        for (Row row : rows){
            rowStack.push(row.reverse(i));
            i++;
        }
        i = 0;
        while(!rowStack.empty()){
            board.rows[i] = rowStack.pop();
            i++;
        }
        return board;
    }

    /**
     * determines if the game is over for the player whose turn it is
     * this is accomplished by iterating over the board and checking for the existence of pieces belonging to the active player
     * every time such a piece is found, possible moves are checked for validity
     * if no valid move is found for any active-player-owned piece on the board, the game is over
     * @given the board is assumed to be reversed if and only if it is the white player's turn (ie Piece.color.WHITE is passed in)
     * @param color the color of the active player, whose pieces must be chacked for valid moves
     * @return true if the active player has no pieces with any valid moves, false if they are able to make a valid move this turn
     */
    public boolean checkGameOver(Piece.color color){
        boolean ret = true;
        Iterator<Row> boardIter = new BoardIterator();
        Row currentRow;
        Square currentSquare;
        while (ret && boardIter.hasNext()){    //iterate over the rows of the board
            currentRow = boardIter.next();
            Iterator<Square> rowIter = currentRow.iterator();
            while(ret && rowIter.hasNext()){       //iterate over the squares of the row
                currentSquare = rowIter.next();
                if(currentSquare.hasPiece()){           //check for the existence of a piece in this square
                    if(currentSquare.getPiece().getColor().equals(color)){  //check if the piece belongs to the active player
                        Piece currentPiece = currentSquare.getPiece();
                        Position currentPosition = new Position(currentRow.getIndex(), currentSquare.getCellIdx());

                        if(currentPiece.getType().equals(Piece.pieceType.KING)){    //checks for possible king piece moves
                            if(currentSquare.getCellIdx() > 0 && currentRow.getIndex() > 0){    //forward-left regular move?
                                Position newPosition = new Position(currentRow.getIndex()-1, currentSquare.getCellIdx()-1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() < 7 && currentRow.getIndex() > 0){    //forward-right regular move?
                                Position newPosition = new Position(currentRow.getIndex()-1, currentSquare.getCellIdx()+1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 1 && currentRow.getIndex() > 1){    //forward-left capture move?
                                Position newPosition = new Position(currentRow.getIndex()-2, currentSquare.getCellIdx()-2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 1 && currentRow.getIndex() < 6){    //forward-right capture move?
                                Position newPosition = new Position(currentRow.getIndex()-2, currentSquare.getCellIdx()+2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 0 && currentRow.getIndex() < 7){    //backward-left regular move?
                                Position newPosition = new Position(currentRow.getIndex()+1, currentSquare.getCellIdx()-1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() < 7 && currentRow.getIndex() < 7){    //backward-right regular move?
                                Position newPosition = new Position(currentRow.getIndex()+1, currentSquare.getCellIdx()+1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 1 && currentRow.getIndex() < 6){    //backward-left capture move?
                                Position newPosition = new Position(currentRow.getIndex()+2, currentSquare.getCellIdx()-2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() < 6 && currentRow.getIndex() < 6){    //backward-right capture move?
                                Position newPosition = new Position(currentRow.getIndex()+2, currentSquare.getCellIdx()+2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }
                        } else {    //checks for possible non-king piece moves (these are a subset of the previous checks)

                            if(currentSquare.getCellIdx() > 0 && currentRow.getIndex() > 0){    //forward-left regular move?
                                Position newPosition = new Position(currentRow.getIndex()-1, currentSquare.getCellIdx()-1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() < 7 && currentRow.getIndex() > 0){    //forward-right regular move?
                                Position newPosition = new Position(currentRow.getIndex()-1, currentSquare.getCellIdx()+1);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.SINGLE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 1 && currentRow.getIndex() > 1){    //forward-left capture move?
                                Position newPosition = new Position(currentRow.getIndex()-2, currentSquare.getCellIdx()-2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }

                            if(currentSquare.getCellIdx() > 1 && currentRow.getIndex() > 6){    //forward-right capture move?
                                Position newPosition = new Position(currentRow.getIndex()-2, currentSquare.getCellIdx()+2);
                                Move forwardLeftRegularMove = new Move(currentPosition, newPosition);
                                if(isValidMove(forwardLeftRegularMove).equals(Move.moveType.CAPTURE)){
                                    ret = false;
                                }
                            }
                        }

                    }
                }

            }
        }
        return ret;
    }

    /**
     * Iterates over the rows of the board
     */
    private class BoardIterator implements Iterator<Row>{
        int cursor;
        Row current;

        public BoardIterator(){
            cursor = -1;
            current = rows[0];
        }

        @Override
        public boolean hasNext() {
            return cursor != 7 && (rows[cursor + 1] != null);
        }

        @Override
        public Row next() {
            if(hasNext()){
                cursor++;
                current = rows[cursor];
                return current;
            }else{
                return null;
            }
        }
    }


    @Override
    public Iterator<Row> iterator() {
        return new BoardIterator();
    }
}

