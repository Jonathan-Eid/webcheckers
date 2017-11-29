package com.webcheckers.model;

/**
 * This class allows for special board creation based on username input in order to easily test game conditions
 */
public class BoardMaker {

    /**
     * Creates a board to check if the red player can win
     * @return
     */
    public Board redWinsBoard(){
        Board board = new Board(true);
        board.addPiece(3, 2, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(2, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    /**
     * Creates a board to check if the white player can win
     * @return
     */
    public Board whiteWinsBoard(){
        Board board = new Board(true);
        board.addPiece(4, 3, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(2, 1, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(2, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(2, 5, Piece.color.WHITE, Piece.pieceType.SINGLE);

        return board;
    }

    /**
     * Creates a board to check if the players can king their pieces
     * @return
     */
    public Board makeKingBoard(){
        Board board = new Board(true);
        board.addPiece(1, 4, Piece.color.RED, Piece.pieceType.SINGLE);
        board.addPiece(6, 3, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    /**
     * Creates a board that sets up chain jumps for a king piece
     * @return
     */
    public Board kingJumpBoard(){
        Board board = new Board(true);
        board.addPiece(0, 3, Piece.color.RED, Piece.pieceType.KING);
        board.addPiece(1, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(1, 6, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 2, Piece.color.WHITE, Piece.pieceType.SINGLE);
        board.addPiece(3, 4, Piece.color.WHITE, Piece.pieceType.SINGLE);
        return board;
    }

    /**
     * Creates a board that sets up a chain capture for regular pieces
     * @return
     */
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
