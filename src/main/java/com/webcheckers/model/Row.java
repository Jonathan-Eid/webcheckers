package com.webcheckers.model;

import java.util.Iterator;

/**
 * Initializes the rows for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Row implements Iterable<Square>{

    private int index;
    private Square[] spaces;

    public Row(int index){
        this.index=index;
        this.spaces = new Square[8];
    }

    public int getIndex(){
        return index;
    }

    public Square getSpace(int cellIdx){
        return spaces[cellIdx];
    }

    /**
     * Set space in a given cell index in the row with a specified color
     * @param cellIdx
     * @param color
     */
    public void setSpace(int cellIdx, Square.color color){
        spaces[cellIdx] = new Square(cellIdx,color);
    }

    private class RowIterator implements Iterator<Square> {
        int cursor;
        Square current;

        public RowIterator(){
            cursor = -1;
            current = spaces[0];
        }

        @Override
        public boolean hasNext() {
            return cursor != 7 && (spaces[cursor + 1] instanceof Square);
        }

        @Override
        public Square next() {
            if(hasNext()){
                cursor += 1;
                current = spaces[cursor];
                return current;
            }else{
                return null;
            }
        }
    }

    @Override
    public Iterator<Square> iterator() {
        return new RowIterator();
    }
}
