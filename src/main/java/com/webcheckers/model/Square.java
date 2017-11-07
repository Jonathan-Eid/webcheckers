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

    /**
     * standard square constructor
     * @param cellLocation the position of this square within a row
     * @param color the color of this square
     */
    public Square(int cellLocation, color color){
        this.cellIdx=cellLocation;
        this.color = color;
    }

    /**
     * copy constructor, creates a deep copy of the square provided
     * @param other a square to copy
     */
    public Square(Square other){
        this.cellIdx = other.cellIdx;
        this.color = other.color;
        if (other.getPiece() != null) {
            this.piece = new Piece(other.piece);
        }
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

    /**
     * checks if it is okay to put a piece here
     * @return true if the piece is an unoccupied red square, false otherwise
     */
    public boolean isValid(){
        return (color.equals(color.RED) & !hasPiece());

    }
}
