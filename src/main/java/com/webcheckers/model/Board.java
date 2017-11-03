package com.webcheckers.model;

import java.util.Iterator;
import java.util.Stack;

/**
 *creates a template for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Board implements Iterable, Cloneable{

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
        for (int x = 0; x < other.rows.length; x ++){
            this.rows[x] = new Row(other.rows[x]);
        }
    }

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
                //TODO
                //return (deltaY == 1 & Math.abs(deltaX) == 1);
            } else if (startPiece.getColor().equals(Piece.color.RED)) {
                //TODO
                //return (deltaY == -1 & Math.abs(deltaX) == 1);
            }
        }
        return Move.moveType.INVALID;
    }

    /**
     * takes a move object and make a change to the board, assuming the move is valid
     * @param move any validated Move object
     */
    public void makeMove(Move move, Move.moveType type){
        //TODO
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

