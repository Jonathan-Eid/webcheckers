package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Piece {
    public enum colors{RED, WHITE}
    private boolean king;
    private colors pieceColor;

    public Piece(colors pieceColor){
        this.king = false;
        this.pieceColor = pieceColor;
    }

    public void kingMe(){
        this.king = true;
    }

    public boolean isKing(){
        return king;
    }
}
