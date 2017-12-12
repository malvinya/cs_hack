package com.example.cshack.model;

/**
 * Created by malva on 12/12/17.
 */
public class MockApiImpl implements MazeApi {

    private PointDto start;
    private PointDto finish;
    private MazeMap map;
    private PointDto currentPosition;

    public MockApiImpl(PointDto start, PointDto finish, MazeMap map) {

        this.start = start;
        this.finish = finish;
        this.map = map;
        this.currentPosition = start;
    }

    public MockApiImpl() {
        this.map = new MazeMapImpl(5);

		map.markWall(0,0);
		map.markWall(0,1);
		map.markWall(0,2);
		map.markWall(0,3);
		map.markWall(0,4);

		map.markWall(4,0);
		map.markWall(4,1);
		map.markWall(4,2);
		map.markWall(4,3);
		map.markWall(4,4);

		map.markWall(1,0);
		map.markWall(2,0);
		map.markWall(3,0);
		map.markWall(1,4);
		map.markWall(2,4);
		map.markWall(3,4);

		map.markWall(2,2);
		map.markWall(1,3);

		map.markStart(1,1);
		map.markFinish(2,3);
        start = new PointDto(1,1);
        finish = new PointDto(2,3);
		currentPosition = new PointDto(1,1);
    }

    @Override
    public MazeInitResponseDto init() {
        MazeInitResponseDto mazeInitRequestDto = new MazeInitResponseDto();
        mazeInitRequestDto.setStartPoint(start);
        mazeInitRequestDto.setEndPoint(finish);
        return mazeInitRequestDto;
    }

    @Override
    public MazeMoveResponseDto move(Direction direction) {
        System.out.println(map.toString());
        PointDto tryingToMoveToPoint = PointDto.getPointForDir(direction, currentPosition);

        if (isWithinBoundary(tryingToMoveToPoint)) {
            FieldType field = map.getField(tryingToMoveToPoint.getX(), tryingToMoveToPoint.getY());
            System.out.println(field);
            if (field.equals(FieldType.Wall)) {
                System.out.println(currentPosition);

                return new MazeMoveResponseDto(currentPosition, "#", "failure");
            } else if (field.equals(FieldType.Floor) || field.equals(FieldType.Finish) || field.equals(FieldType.Start)) {
                currentPosition = tryingToMoveToPoint;
                System.out.println(currentPosition);

                return new MazeMoveResponseDto(tryingToMoveToPoint, " ","success");
            } else {
                throw  new IllegalStateException();
            }
        } else {
            System.out.println("out:" + currentPosition);
            return new MazeMoveResponseDto(currentPosition, "#", "failure");
        }

    }

    @Override
    public MazeScanResponseDto scan() {
        return null;
    }

    @Override
    public ScanDirectionResponseDto scanDirection(Direction direction) {
        return null;
    }

    private boolean isWithinBoundary(PointDto point) {
        return point.getX() >=0
                && point.getY() >=0
                && point.getX() < 5
                && point.getY() < 5;
    }
}
