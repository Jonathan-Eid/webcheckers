package com.webcheckers.model;

/**
 * creates a template for the squares on the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Square {
    private int cellIdx;
    public enum color{RED, EMPTY_WHITE}
    private color color;
    private Piece piece = null;

    public Square(int cellLocation, color color){
        this.cellIdx=cellLocation;
        this.color = color;
    }

    public Square(Square other){
        this.cellIdx = other.cellIdx;
        this.color = other.color;
        this.piece = new Piece(other.piece);
    }


    public int getCellIdx() {
        return cellIdx;
    }


    public color getColor() {
        return color;
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
        return (color.equals(color.RED) & !hasPiece());

    }
}
