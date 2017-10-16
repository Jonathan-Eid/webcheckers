package com.webcheckers.model;

/**
 * creates a template for the squares on the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Square {
    private int cellIdx;
    public enum spotType{RED, EMPTY_WHITE}
    private spotType spot;
    private Piece piece = null;

    public Square(int cellLocation, spotType spot){
        this.cellIdx=cellLocation;
        this.spot = spot;
    }


    public int getCellIdx() {
        return cellIdx;
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

    public boolean hasPiece(){
        return this.piece != null;
    }

    public boolean isValid(){
        return (spot.equals(spotType.RED) & !hasPiece());

    }
}
