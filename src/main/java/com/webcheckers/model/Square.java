package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Square {
    private int x;
    private int y;
    public enum spotType{RED, EMPTY_WHITE,RED_KING,WHITE_KING}
    private spotType spot;
    private Piece piece = null;

    public Square(int x_location, int y_location,spotType spot){
        this.x=x_location;
        this.y=y_location;
        this.spot = spot;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public spotType getSpot() {
        return spot;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
