package com.webcheckers.model;

import java.util.Stack;

/**
 * Created by Juna on 10/31/2017.
 */
public class Turn {

    private Player player;
    private Piece.color color;
    private Stack<Board> undoBoards;


    public Turn(Player player, Piece.color color){
        this.player=player;
        this.color = color;
        undoBoards = new Stack<>();
    }

    public Player getPlayer() {
        return player;
    }

    public Piece.color getColor() {
        return color;
    }

    public void pushBoards(Board board){
        undoBoards.push(board);
    }

    public Board undo(){
        return undoBoards.pop();
    }


}
