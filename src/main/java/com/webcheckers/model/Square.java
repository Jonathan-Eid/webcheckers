package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
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
        return (spot == spotType.RED & !hasPiece());

    }
}
