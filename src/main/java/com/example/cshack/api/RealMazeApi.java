package com.example.cshack.api;

import com.example.cshack.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

/**
 * Created by Tomasz on 12.12.2017.
 */
@Service
public class RealMazeApi implements MazeApi {

    private final RestTemplate restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(RealMazeApi.class);

    private String baseUrl = "http://192.168.0.101:12345/maze-game";

    private String teamId = "bvc234a6";

    private String mazeId = "newbie";

    public RealMazeApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        DefaultUriTemplateHandler h = new DefaultUriTemplateHandler();
        h.setBaseUrl(baseUrl);
        this.restTemplate.setUriTemplateHandler(h);
    }
    @Override
    public MazeInitResponseDto init() {

        MazeInitRequestDto ir = getMazeInitRequestDto();
        //HttpEntity<MazeInitRequestDto> requestEntity = new HttpEntity<>(ir);

        MazeInitResponseDto r = this.restTemplate.postForObject("/StartCompetition", ir, MazeInitResponseDto.class);
        LOG.info("response: {}", r);
        return r;
    }

    private MazeInitRequestDto getMazeInitRequestDto() {
        MazeInitRequestDto ir = new MazeInitRequestDto();
        ir.setTeamId(teamId);
        ir.setMazeId(mazeId);
        LOG.info("init: {}", ir);
        return ir;
    }

    @Override
    public MazeMoveResponseDto move(Direction direction) {

        String method = "";
        switch (direction) {
            case Up: method = "/MoveUp"; break;
            case Down: method = "/MoveDown"; break;
            case Left: method = "/MoveLeft"; break;
            case Right: method = "/MoveRight"; break;
        }

        MazeInitRequestDto ir = getMazeInitRequestDto();
        MazeMoveResponseDto moveResponse = this.restTemplate.postForObject(method, ir, MazeMoveResponseDto.class);

        return moveResponse;
    }
}
