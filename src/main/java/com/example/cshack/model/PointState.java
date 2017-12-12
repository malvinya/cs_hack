package com.example.cshack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PointState {
    PointDto pointDto;
    boolean visited;
    Direction direction;
}