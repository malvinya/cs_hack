package com.example.cshack.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
public class MazeScanResponseDto {

    Map<Direction, FieldType> fields;

}
