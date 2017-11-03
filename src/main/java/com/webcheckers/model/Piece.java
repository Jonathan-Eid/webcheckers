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

    public Piece(color pieceColor){
        type = pieceType.SINGLE;
        this.pieceColor = pieceColor;
    }

    public Piece(Piece other){
        this.type = other.type;
        this.pieceColor = other.pieceColor;
    }

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
