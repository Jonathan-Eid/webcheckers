package com.webcheckers.model;

/**
 * Initializes position ot be used for the pieces and squares in the checkers game
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Position {
    private int row;
    private int cell;

    /**
     * standard constructor for position
     * @param row the row coordinate
     * @param cell the column coordinate
     */
    public Position(int row,int cell){
        this.row=row;
        this.cell=cell;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    public boolean equals (Object obj){
        if (obj instanceof Position){
            Position p = (Position)obj;
            if(p.row == this.row){
                if(p.cell == this.cell){
                    return true;
                }
            }
        }
        return false;
    }
}
