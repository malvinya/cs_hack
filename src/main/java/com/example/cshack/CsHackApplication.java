package com.example.cshack;

import com.example.cshack.model.*;
import com.example.cshack.tools.MapLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsHackApplication implements CommandLineRunner{

	private final Logger LOG = LoggerFactory.getLogger(CsHackApplication.class);

	@Autowired
	MazeApi api;

	@Autowired
	MapLoader mapLoader;

	public static void main(String[] args) {
		SpringApplication.run(CsHackApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Hello world");
//		map.markWall(0,0);
//		map.markWall(0,1);
//		map.markWall(0,2);
//		map.markWall(0,3);
//		map.markWall(0,4);
//
//		map.markWall(4,0);
//		map.markWall(4,1);
//		map.markWall(4,2);
//		map.markWall(4,3);
//		map.markWall(4,4);
//
//		map.markWall(1,0);
//		map.markWall(2,0);
//		map.markWall(3,0);
//		map.markWall(1,4);
//		map.markWall(2,4);
//		map.markWall(3,4);
//
//		map.markWall(2,2);
//		map.markWall(1,3);
//
//		map.markStart(1,1);
//		map.markFinish(2,3);

		//MazeMap testMap = mapLoader.Load("map1.txt");
		//LOG.info("\n{}\n", testMap.toString());

		//MockApiImpl mockApi = new MockApiImpl();
		//RunAlgo(mockApi);

		RunAlgo(api);
	}

	private void RunAlgo(MazeApi api) {
		MazeMap map = new MazeMapImpl();

		MazeInitResponseDto responseDto = api.init();
		PointDto start = responseDto.getStartPoint();
		PointDto finish = responseDto.getEndPoint();
		MazeSolver solver = new MazeSolver(start,map, finish, api);
		solver.solve();
	}
}
