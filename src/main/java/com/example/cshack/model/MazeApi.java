package com.example.cshack.model;

/**
 * Created by Tomasz on 12.12.2017.
 */
public interface MazeApi {

    MazeInitResponseDto init();
    MazeMoveResponseDto move(Direction direction);
    MazeScanResponseDto scan();
    ScanDirectionResponseDto scanDirection(Direction direction);

}


