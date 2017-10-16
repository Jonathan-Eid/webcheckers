package com.webcheckers.model;

/**
 * Initializes the rows for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Row {

    private int index;
    private Square[] spaces;

    public Row(int index){
        this.index=index;
        this.spaces = new Square[7];
    }

    public int getIndex(){
        return index;
    }

}
