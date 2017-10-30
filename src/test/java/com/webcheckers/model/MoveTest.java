package com.webcheckers.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dis446 on 10/26/17.
 */
public class MoveTest {

    private Move CuT;

    @Before
    public void setUp() throws Exception {
        CuT = new Move(new Position(0,0), new Position(0,0));

    }

    @Test
    public void getStart() throws Exception {
        assertEquals(CuT.getStart(), new Position(0,0));
    }

    @Test
    public void getEnd() throws Exception {
        assertEquals(CuT.getEnd(), new Position(0,0));
    }

}