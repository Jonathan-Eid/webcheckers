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

    /**
     * Initializes the board with Rows containing spaces that contain pieces
     */
    public Board(){
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

    public Board(Board other){
        this.rows = new Row[other.rows.length];
        for (int i = 0; i < this.rows.length; i++){
            this.rows[i] = new Row(other.rows[i]);
        }
    }

    /**
     * determine if a move is valid, and whether is a single or capture move
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
    }

    /**
     * takes a move object and make a change to the board, assuming the move is valid
     * @param move any validated Move object
     */
    public void makeMove(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();
        Square startSquare = rows[start.getRow()].getSpace(start.getCell());
        Square endSquare = rows[end.getRow()].getSpace(end.getCell());
        endSquare.setPiece(startSquare.getPiece());
        startSquare.setPiece(null);
        //TODO Check if capture move and change the board accordingly.
        if (move.type.equals(Move.moveType.CAPTURE)) {
            //TODO
        }
    }

    public Board reverse(){
        Board board = new Board();
        Stack<Row> rowStack = new Stack<>();
        int i = 0;
        for (Row row : rows){
            rowStack.push(row.reverse(i));
        }
        i = 0;
        while(!rowStack.empty()){
            board.rows[i] = rowStack.pop();
            i++;
        }
        return board;
    }

    public Move reverseMove(Move move){
        Position start = move .getStart();
        Position end = move .getEnd();
        int startX = start.getRow();
        int startY = start.getCell();
        int endX = end.getRow();
        int endY = end.getCell();

        Position newStart = new Position(7 - startX, 7 - startY);
        Position newEnd = new Position(7 - endX, 7 - endY);
        return new Move(newStart, newEnd);
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

