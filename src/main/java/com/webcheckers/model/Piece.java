package com.webcheckers.model;

import java.lang.reflect.Type;

/**
 * creates a template for the piece object
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Piece {
    public enum colors{RED, WHITE}
    public enum pieceType{SINGLE, KING}
    private colors pieceColor;
    private pieceType type;

    public Piece(colors pieceColor){
        type = pieceType.SINGLE;
        this.pieceColor = pieceColor;
    }

    public void kingMe(){
        type = pieceType.KING;
    }

    public pieceType getType(){
        return type;
    }

    public colors getPieceColor() {

        return pieceColor;
    }
}
