package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
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
