package com.webcheckers.model;

import org.junit.Before;
import org.junit.Test;
import spark.TemplateEngine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dis446 on 10/26/17.
 */
public class BoardTest {
    private Board CuT;

    private Move invalidMove;
    private Move singleMove;
    private Move captureMove;
    private Position startPosition;
    private Position endPosition;

    @Before
    public void setUp() throws Exception {
        invalidMove = mock(Move.class);
        singleMove = mock(Move.class);
        captureMove = mock(Move.class);
        startPosition = mock(Position.class);
        endPosition = mock(Position.class);

        doReturn(0).when(startPosition).getCell();
        doReturn(5).when(startPosition).getRow();
        doReturn(1).when(endPosition).getCell();
        doReturn(4).when(endPosition).getRow();

        assertEquals(Move.moveType.SINGLE, CuT.isValidMove(new Move(startPosition, endPosition)));
        //when(session.attribute("player")).thenReturn(player);

        // create a unique CuT for each test

    }

    @Test
    public void isValidMove() throws Exception {
        CuT = new Board();
    }

    @Test
    public void reverse() throws Exception {

    }

    @Test
    public void iterator() throws Exception {

    }

}