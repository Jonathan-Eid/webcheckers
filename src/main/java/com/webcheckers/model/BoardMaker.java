package com.webcheckers.model;

public class BoardMaker {
    public Board redWinsBoard(){
        Board board = new Board(true);
        board.addPiece(3, 2, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(2, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    public Board whiteWinsBoard(){
        Board board = new Board(true);
        board.addPiece(4, 3, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(2, 1, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(2, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(2, 5, Piece.color.WHITE, Piece.pieceType.SINGLE);

        return board;
    }

    public Board makeKingBoard(){
        Board board = new Board(true);
        board.addPiece(1, 4, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(6, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    public Board kingJumpBoard(){
        Board board = new Board(true);
        board.addPiece(0, 3, Piece.color.RED, Piece.pieceType.KING);
        board.addPiece(1, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(1, 6, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 4, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    public Board chainJumpBoard(){
        Board board = new Board(true);
        board.addPiece(4, 1, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(1, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(1, 4, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 4, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }



}
