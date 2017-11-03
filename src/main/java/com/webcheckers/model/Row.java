package com.webcheckers.model;

import spark.Route;

import java.util.Iterator;
import java.util.Stack;

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

    /**Copy Constructor
     * @param other
     */
    public Row(Row other){
        this.index = other.getIndex();
        this.spaces = new Square[other.spaces.length];
        for (int i = 0; i < other.spaces.length; i++){
            this.spaces[i] = new Square(other.spaces[i]);
        }
    }

    public Row reverse(int index){
        Row row = new Row(index);
        Stack<Square> squareStack = new Stack<>();
        for (Square square: spaces){
            squareStack.push(square);
        }
        int i = 0;
        while (!squareStack.empty()){
            row.spaces[i] = squareStack.pop();
            i++;
        }
        return row;
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
