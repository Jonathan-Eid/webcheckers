package com.webcheckers.model;

import java.util.Iterator;
import java.util.Map;

/**
 *creates a template for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Board implements Iterable{

    private Row[] rows;

    public Board(){
        this.rows = new Row[7];
        for (int y = 0; y < 8; y++){
            for (int x = 0; x < 8; x++){
                if (y < 3){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.WHITE));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.WHITE));
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else if (y > 4){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.RED));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.color.RED));
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else{
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
            }
        }
    }

    private class BoardIterator implements Iterator<Row>{
        int cursor;
        Row current;

        public BoardIterator(){
            cursor = 0;
            current = rows[0];
        }

        @Override
        public boolean hasNext() {
            return (rows[cursor+1] instanceof Row);
        }

        @Override
        public Row next() {
            if(hasNext()){
                cursor += 1;
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

