package com.example.cshack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDto {

    private int x;
    private int y;

    public static PointDto getPointForDir(Direction dir, PointDto p) {
        switch (dir) {
            case Up:
                return new PointDto(p.getX(), p.getY()-1);
            case Down:
                return new PointDto(p.getX(), p.getY()+1);
            case Left:
                return new PointDto(p.getX()-1, p.getY());
            case Right:
                return new PointDto(p.getX()+1, p.getY());
            default:
                throw new RuntimeException("Not supported");
        }
    }

    public static Direction getCounterDirection(Direction dir) {
        Direction cdir;
        switch (dir) {
            case Up: cdir = Direction.Down; break;
            case Down: cdir = Direction.Up; break;
            case Left: cdir = Direction.Right; break;
            case Right: cdir = Direction.Left; break;
            default:
                throw new RuntimeException("Not supported");
        }
        return cdir;
    }

    public static PointDto getCounterPointForDir(Direction dir, PointDto p) {
        Direction cdir = getCounterDirection(dir);
        return getPointForDir(cdir, p);
    }

}
