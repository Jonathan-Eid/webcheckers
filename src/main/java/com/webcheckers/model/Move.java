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

    /**
     * standard constructor
     * @param start the position (of a piece) before it moves
     * @param end the position (of a piece) after it moves
     */
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
        type = moveType.INVALID;
    }

    /**
     * change the type of the move
     * moves are initially INVALID, but may be changed to SINGLE or CAPTURE once they are validated
     * @param type the type to change this move to (INVALID, SINGLE, CAPTURE)
     */
    public void setType(moveType type){
        this.type = type;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    /*
    //this is unused and can probably be removed
    public Move reverse() {
        Position start = new Position(7-this.getStart().getRow(),7-this.getStart().getCell());
        Position end = new Position(7-this.getEnd().getRow(),7-this.getEnd().getCell());
        return new Move(start,end);
    }
     */
}
