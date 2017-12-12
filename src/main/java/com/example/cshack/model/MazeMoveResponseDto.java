package com.example.cshack.model;

import lombok.Data;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
public class MazeMoveResponseDto {

    private PointDto position;
    private String details;
    private String outcome;

    public FieldType getFieldType() {
        if (details.equals("#")) {
            return FieldType.Wall;
        }
        if (details.equals(" ")) {
            return FieldType.Floor;
        }
        return FieldType.Unknown;
    }

    public boolean IsSuccess() {
        return outcome.equals("success");
    }
}
