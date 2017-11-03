package com.webcheckers.model;

/**
 * a class for the action of moving a piece
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Move {
    public enum moveType{INVALID, SINGLE, CAPTURE}

    private Position start;
    private Position end;
    public moveType type;

    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        type = moveType.INVALID;
    }

    public void setType(moveType type){
        this.type = type;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }


}
