package com.example.cshack.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by malva on 12/12/17.
 */
public class MockApiImplTest {

    @Test
    public void testApi() throws Exception {
        MockApiImpl api = new MockApiImpl();
        System.out.println(api.init());
        api.move(Direction.Right);
        api.move(Direction.Right);
        api.move(Direction.Down);

    }
}