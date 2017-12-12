package com.example.cshack.model;

import java.util.Arrays;

/**
 * Created by malva on 12/12/17.
 */
public class MazeMapImpl implements MazeMap {

    FieldType[][] map;


    public MazeMapImpl() {
        map = new FieldType[1000][1000];
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map.length; j++) {
                map[i][j] = FieldType.Unknown;
            }
        }

    }

    public MazeMapImpl(int size) {
        map = new FieldType[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                map[i][j] = FieldType.Unknown;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map.length; j++) {
                b.append(" "+map[j][i].name()+" ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    @Override
    public FieldType getField(int i, int j) {
        return map[i][j];
    }

    @Override
    public void markFloor(int i, int j) {
        map[i][j] = FieldType.Floor;
    }

    @Override
    public void markWall(int i, int j) {
        map[i][j] = FieldType.Wall;

    }

    @Override
    public void markStart(int i, int j) {
        map[i][j] = FieldType.Start;

    }

    @Override
    public void markFinish(int i, int j) {
        map[i][j] = FieldType.Finish;

    }

    @Override
    public void mark(int i, int j, FieldType fieldType) {
        map[i][j] = fieldType;
    }
}
