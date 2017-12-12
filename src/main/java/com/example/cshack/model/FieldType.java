package com.example.cshack.model;

/**
 * Created by Tomasz on 12.12.2017.
 */
public enum FieldType {


    Unknown("?"),
    Wall("#"),
    Floor(" "),
    Start("A"),
    Finish("B");

    private String rep;

    FieldType(String rep) {
        this.rep = rep;
    }

    String rep() {
        return rep;
    }

}
