package com.webcheckers.model;

/**
 * Created by Juna on 10/15/2017.
 */
public class Board {

    private Square[][] board;
    private int dim;

    public Board (int dimensions){
        this.dim = dimensions-1;
        this.board = new Square[dim-1][dim-1];
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

                    }
                }
            }
        }
    }
}
}
