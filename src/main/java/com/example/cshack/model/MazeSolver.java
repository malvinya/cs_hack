package com.example.cshack.model;

import org.apache.commons.logging.Log;
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

        /*
public void dfs()
{
    //DFS uses Stack data structure
    Stack s=new Stack();
    s.push(this.rootNode);
    rootNode.visited=true;
    printNode(rootNode);
    while(!s.isEmpty())
    {
        Node n=(Node)s.peek();
        Node child=getUnvisitedChildNode(n);
        if(child!=null)
        {
            child.visited=true;
            printNode(child);
            s.push(child);
        }
        else
        {
            s.pop();
        }
    }
    //Clear visited property of nodes
    clearNodes();
}
         */

        Stack<PointState> q = new Stack<>();
        //map.markStart(start.getX(), start.getY());
        map.mark(start.getX(), start.getY(), FieldType.Visited);
        map.markFinish(end.getX(), end.getY());

        PointState pointState = new PointState(start, true, Direction.Up);
        q.add(pointState);

        log.info(pointState.toString());

        while (!q.isEmpty()) {
            PointState currentState = q.peek();
            PointState child = getUnvisitedChildNode(currentState);
            if(child != null)
            {
                map.mark(child.getPointDto().getX(), child.getPointDto().getY(), FieldType.Visited);
                //printNode(child);
                q.push(child);
            }
            else
            {
                // return
                PointState prev = q.pop();
                Direction cdir = PointDto.getCounterDirection(prev.direction);
                MazeMoveResponseDto move = api.move(cdir);
                if (!q.isEmpty()) {
                    PointState c = q.peek();
                    assertPositionAfterMove(c.getPointDto(), move);
                }
            }
        }
    }


    private PointState getUnvisitedChildNode(PointState curentState){
        for (Direction dir: Direction.values()) {
            PointDto point = PointDto.getPointForDir(dir, curentState.getPointDto());
            FieldType mapField = map.getField(point.getX(), point.getY());
            switch (mapField) {
                case Finish:
                case Unknown:
                    MazeMoveResponseDto move = api.move(dir);
                    FieldType foundField = move.getFieldType();
                    map.mark(point.getX(), point.getY(), foundField);

                    if (mapField == FieldType.Finish) {
                        throw new RuntimeException("Finished!");
                        //return new PointState(point, false);
                    }

                    if (move.IsSuccess()) {
                        assertPositionAfterMove(point, move);
                        map.mark(point.getX(), point.getY(), FieldType.Visited);
                        return new PointState(point, false, dir);
                    }
                    break;

            }
        }
        return null;
    }

    private void assertPositionAfterMove(PointDto point, MazeMoveResponseDto move) {
        if (move.getPosition().getX() != point.getX() || move.getPosition().getY() != point.getY()) {
            throw new RuntimeException("Map not in sync with API");
        }
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
