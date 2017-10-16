package com.webcheckers.model;

import java.util.Map;

/**
 *creates a template for the board
 * Created by Juna, Disney, Andy, Ani on 10/15/2017.
 */
public class Board {

    private Row[] rows;

    public Board(){
        this.rows = new Row[7];
        for (int y; y < 8; y++){
            for (int x; x < 8; x++){
                if (y < 3){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.colors.WHITE));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.colors.WHITE));
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else if (y > 4){
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.colors.RED));
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                            rows[y].getSpace(x).setPiece(new Piece(Piece.colors.RED));
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
                else{
                    if(y % 2 == 0){
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.RED);
                        }
                    }
                    else{
                        if (x % 2 == 0){
                            rows[y].setSpace(x, Square.spotType.RED);
                        }
                        else{
                            rows[y].setSpace(x, Square.spotType.EMPTY_WHITE);
                        }
                    }
                }
            }
        }
    }





}

