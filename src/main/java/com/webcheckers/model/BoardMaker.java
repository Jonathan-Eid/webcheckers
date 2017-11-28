package com.webcheckers.model;

public class BoardMaker {
    public Board redWinsBoard(){
        Board board = new Board(true);
        board.addPiece(3, 4, Piece.color.RED);
        board.addPiece(2, 3, Piece.color.WHITE);
        return board;
    }

    public Board whiteWinsBoard(){
        Board board = new Board(true);
        board.addPiece(3, 2, Piece.color.RED);
        board.addPiece(2, 3, Piece.color.WHITE);
        board.addPiece(1, 4, Piece.color.WHITE);
        return board;
    }

    public Board makeKingBoard(){
        Board board = new Board(true);
        board.addPiece(1, 4, Piece.color.RED);
        board.addPiece(6, 3, Piece.color.WHITE);
        return board;
    }

    public Board kingJumpBoard(){
        Board board = new Board(true);
        board.addPiece(0, 3, Piece.color.RED);
        board.addPiece(1, 2, Piece.color.WHITE);
        board.addPiece(1, 4, Piece.color.WHITE);
        board.addPiece(3, 2, Piece.color.WHITE);
        board.addPiece(3, 4, Piece.color.WHITE);
        return board;
    }

    public Board chainJumpBoard(){
        Board board = new Board(true);
        board.addPiece(4, 1, Piece.color.RED);
        board.addPiece(1, 2, Piece.color.WHITE);
        board.addPiece(1, 4, Piece.color.WHITE);
        board.addPiece(3, 2, Piece.color.WHITE);
        board.addPiece(3, 4, Piece.color.WHITE);
        return board;
    }



}
