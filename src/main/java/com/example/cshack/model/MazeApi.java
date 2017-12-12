package com.example.cshack.model;

/**
 * Created by Tomasz on 12.12.2017.
 */
public interface MazeApi {

    MazeInitResponseDto init(MazeInitRequestDto request);
    MazeMoveResponseDto move(Direction direction);

}


