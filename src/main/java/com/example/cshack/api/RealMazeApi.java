package com.example.cshack.api;

import com.example.cshack.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.teamId}")
    private String teamId;

    @Value("${api.mazeId}")
    private String mazeId;

    public RealMazeApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        DefaultUriTemplateHandler h = new DefaultUriTemplateHandler();
        h.setBaseUrl(baseUrl);
        this.restTemplate.setUriTemplateHandler(h);
    }
    @Override
    public MazeInitResponseDto init(MazeInitRequestDto request) {

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
