package com.webcheckers.model;

import java.util.Map;

/**
 * Created by Juna on 10/15/2017.
 */
public class Board {

    private Square[][] board;
    private int dim;

    public Board (int dimensions){
        this.dim = dimensions-1;
        this.board = new Square[dim][dim];
        for (int y = 0; y <= dim; y++){
            for (int x = 0; x <= dim; x++){
                if (y==0){
                    if (x % 2 == 0){
                        board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                    }
                    else{
                        board[x][y] = new Square(x,y, Square.spotType.RED_KING);
                        board[x][y].setPiece(new Piece(Piece.colors.WHITE));

                    }
                }
                else if (y==dim){
                    if (x % 2 == 0){
                        board[x][y] = new Square(x,y, Square.spotType.WHITE_KING);
                        board[x][y].setPiece(new Piece(Piece.colors.RED));
                    }
                    else{
                        board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);

                    }
                }
                else if (y <= 2){
                    if (y % 2 == 0){
                        if (x % 2 == 0){
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.RED);
                            board[x][y].setPiece(new Piece(Piece.colors.WHITE));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            board[x][y] = new Square(x,y, Square.spotType.RED);
                            board[x][y].setPiece(new Piece(Piece.colors.WHITE));                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else if (y >= dim-2){
                    if (y % 2 == 0){
                        if (x % 2 == 0){
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.RED);
                            board[x][y].setPiece(new Piece(Piece.colors.RED));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            board[x][y] = new Square(x,y, Square.spotType.RED);
                            board[x][y].setPiece(new Piece(Piece.colors.RED));                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else{
                    if (y % 2 == 0){
                        if (x % 2 == 0){
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.RED);
                        }
                    }
                    else{
                        if (x % 2 == 0) {
                            board[x][y] = new Square(x, y, Square.spotType.RED);
                        }
                        else{
                            board[x][y] = new Square(x,y, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
            }
        }
    }

    public boolean isValidMove(Square currentSquare, Square finalSquare){
        int startX = currentSquare.getX();
        int startY = currentSquare.getY();
        int endX = finalSquare.getX();
        int endY = finalSquare.getY();
        int deltaX = endX-startX;
        int deltaY = endY-startY;
        Piece piece = currentSquare.getPiece();

        if (finalSquare.getSpot()== Square.spotType.EMPTY_WHITE || finalSquare.hasPiece() || piece==null){return false;}

        if (piece.getPieceColor() == Piece.colors.WHITE){
            if (deltaY==1 & Math.abs(deltaX)==1){
                return true;
            }
        }
        else if(piece.getPieceColor()== Piece.colors.RED){
            if (deltaY==-1 & Math.abs(deltaX)==1){
                return true;
            }
        }

    }

}

