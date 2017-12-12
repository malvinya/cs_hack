package com.example.cshack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
@AllArgsConstructor
public class MazeMoveResponseDto {

    private PointDto position;
    private String details;
    private String outcome;

    public FieldType getFieldType() {
        if (outcome.equals("#")) {
            return FieldType.Wall;
        }
        if (outcome.equals(" ")) {
            return FieldType.Floor;
        }
        return FieldType.Unknown;
    }

    public boolean IsSuccess() {
        return details.equals("success");
    }
}
