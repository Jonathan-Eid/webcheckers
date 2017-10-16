package com.webcheckers.model;

import java.util.Collections;
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

    public Board reverse(){
        Board board = new Board();
        Stack<Row> rowStack = new Stack<>();
        for (Row row : rows){
            rowStack.push(row);
        }
        int i = 0;
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

