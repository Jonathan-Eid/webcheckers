package com.webcheckers.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dis446 on 10/26/17.
 */
public class MessageTest {
    private Message CuT;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void regularMessage() throws Exception {
        CuT = new Message("Hello World!", Message.type.info);

    }

    @Test
    public void nullMessage() throws Exception {
        CuT = new Message(null, Message.type.info);
    }


    @Test
    public void errorMessage() throws Exception {
        CuT = new Message("Error World!", Message.type.error);
    }

}