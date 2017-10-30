package com.webcheckers.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.DefaultEditorKit;

import static org.junit.Assert.*;

/**
 * Created by dis446 on 10/26/17.
 */
public class PieceTest {

    private Piece CuT;

    @Before
    public void setUp() throws Exception {

        CuT = new Piece(Piece.color.RED);

    }

    @Test
    public void getType() throws Exception {
        assertEquals(CuT.getType(), Piece.pieceType.SINGLE);
    }

    @Test
    public void getColor() throws Exception {
        assertEquals(CuT.getColor(), Piece.color.RED);
    }


    @Test
    public void isKing(){
        CuT.kingMe();
        assertEquals(CuT.getType(), Piece.pieceType.KING);
    }

}