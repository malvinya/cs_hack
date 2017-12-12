package com.example.cshack.model;

import lombok.Data;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
public class MoveResponseDto {

    private PointDto position;
    private FieldType details;
    private boolean success;
}
