package com.webcheckers.model;

/**
 * creates a template for the piece object
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Piece {
    public enum color{RED, WHITE}
    public enum pieceType{SINGLE, KING}
    private color pieceColor;
    private pieceType type;

    /**
     * standard constructor
     * @param pieceColor the color of this piece
     */
    public Piece(color pieceColor){
        type = pieceType.SINGLE;
        this.pieceColor = pieceColor;
    }

    /**
     * copy constructor, creates a deep copy of piece
     * @param other the piece to copy
     */
    public Piece(Piece other){
        this.type = other.type;
        this.pieceColor = other.pieceColor;
    }

    /**
     * make the piece a king
     */
    public void kingMe(){
        type = pieceType.KING;
    }

    public pieceType getType(){
        return type;
    }

    public color getColor() {
        return pieceColor;
    }
}
