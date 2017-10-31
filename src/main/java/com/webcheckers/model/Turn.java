package com.webcheckers.model;

/**
 * Created by Juna on 10/31/2017.
 */
public class Turn {

    private Player player;
    private Piece.color color;

    public Turn(Player player, Piece.color color){
        this.player=player;
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece.color getColor() {
        return color;
    }
}
