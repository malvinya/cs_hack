package com.example.cshack.model;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by malva on 12/12/17.
 */
public class MazeSolver {

    private PointDto start;
    private MazeMap map;
    private PointDto end;
    private MazeApi api;

    Logger log = LoggerFactory.getLogger(MazeSolver.class);

    public MazeSolver(PointDto startPoint, MazeMap map, PointDto endPoint, MazeApi api) {
        this.start = startPoint;
        this.map = map;
        this.end = endPoint;
        this.api = api;
    }

    public void solve() {

        Queue<PointState> q = new LinkedList<>();
        map.markStart(start.getX(), start.getY());
        map.markFinish(end.getX(), end.getY());
        PointState pointState = new PointState(start, true);
        q.add(pointState);
        log.info(pointState.toString());
        while (!q.isEmpty()) {
            PointState currentState = q.remove();
            PointState child = null;
            while ((child = getUnvisitedChildNode(currentState)) != null) {
                child.visited = true;
                log.info(child.toString());
                q.add(child);
            }
        }
    }

    private PointState getUnvisitedChildNode(PointState curentState){
        Map<Direction, PointDto> points = new HashMap<>();
        points.put(Direction.Up, new PointDto(curentState.getPointDto().getX(), curentState.getPointDto().getY()-1));
        points.put(Direction.Down, new PointDto(curentState.getPointDto().getX(), curentState.getPointDto().getY()+1));
        points.put(Direction.Left, new PointDto(curentState.getPointDto().getX()-1, curentState.getPointDto().getY()));
        points.put(Direction.Right, new PointDto(curentState.getPointDto().getX()+1, curentState.getPointDto().getY()));

        for (Direction dir: points.keySet()) {
            PointDto point = points.get(dir);
            FieldType field = map.getField(point.getX(), point.getY());
            switch (field) {
                case Finish:
                case Unknown:
                    MazeMoveResponseDto move = api.move(dir);
                    map.mark(point.getX(), point.getY(), move.getFieldType());

                    if (field == FieldType.Finish) {
                        throw new RuntimeException("Finished!");
                        //return new PointState(point, false);
                    }
            
                    if (move.IsSuccess()) {
                        return new PointState(point, false);
                    }
                    break;

            }
        }
        return null;
    }
}


    //
//    public void bfs()
//    {
//        //BFS uses Queue data structure
//        Queue q=new LinkedList();
//        q.add(this.rootNode);
//        printNode(this.rootNode);
//        rootNode.visited=true;
//        while(!q.isEmpty())
//        {
//            Node n=(Node)q.remove();
//            Node child=null;
//            while((child=getUnvisitedChildNode(n))!=null)
//            {
//                child.visited=true;
//                printNode(child);
//                q.add(child);
//            }
//        }
//        //Clear visited property of nodes
//        clearNodes();
//    }
