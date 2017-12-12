package com.example.cshack.model;

/**
 * Created by Tomasz on 12.12.2017.
 */
public interface MazeMap {

    FieldType getField(int i, int j);
    void markFloor(int i, int j);
    void markWall(int i, int j);
    void markStart(int i, int j);
    void markFinish(int i, int j);

}


