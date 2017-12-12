package com.example.cshack.api;

import lombok.Data;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
public class ScanResponseDto {

    private String left;
    private String right;
    private String up;
    private String down;
}
