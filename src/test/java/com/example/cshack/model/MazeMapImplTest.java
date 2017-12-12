package com.example.cshack.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by malva on 12/12/17.
 */
public class MazeMapImplTest {

    @Test
    public void testMap() throws Exception {
        MazeMapImpl mazeMap = new MazeMapImpl(4);
        mazeMap.markStart(2,3);
        mazeMap.markFinish(3,3);
        mazeMap.markFloor(1,1);
        mazeMap.markWall(0,0);
        Assertions.assertThat(mazeMap.getField(1,1)).isEqualTo(FieldType.Floor);
        System.out.println(mazeMap.toString());

    }
}