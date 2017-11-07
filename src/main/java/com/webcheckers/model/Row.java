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

    /**
     * standard constructor
     * @param index the index of this row in a checkers board, with a value of 0 to 7
     */
    public Row(int index){
        this.index=index;
        this.spaces = new Square[8];
    }

    /**Copy Constructor
     * creates a deep copy of the row provided
     * @param other the row to copy
     */
    public Row(Row other){
        this.index = other.getIndex();
        this.spaces = new Square[other.spaces.length];
        for (int i = 0; i < other.spaces.length; i++){
            this.spaces[i] = new Square(other.spaces[i]);
        }
    }

    /**
     * reverse the order of squares in a row, creating a new row object
     * @param index the index of the row produced by this method
     * @return a row with the index provided, but with all of this row's squares in reverse order
     */
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
     * Set square at a given cell index in the row to a specified color
     * @param cellIdx the index of the square being recolored
     * @param color the new color for the square
     */
    public void setSpace(int cellIdx, Square.color color){
        spaces[cellIdx] = new Square(cellIdx,color);
    }

    /**
     * Private class for a row iterator
     */
    private class RowIterator implements Iterator<Square> {
        int cursor;
        Square current;

        /**
         * constructor for the row iterator
         */
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
