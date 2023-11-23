package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Arrays;
import java.util.List;

@Service
public class ScoreServiceRestClient implements ScoreInterface {
    private final String url = "http://localhost:8080/api/score";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) throws ScoreException {
        restTemplate.postForEntity(url, score, Score.class);
    }


    @Override
    public List<Score> getAllScores() throws ScoreException {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + "game", Score[].class).getBody());
    }

    @Override
    public void reset() throws ScoreException {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public List<Score> findUserById(int id) throws ScoreException {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + id, Score[].class).getBody());
    }

    @Override
    public void update(Score score) {
        restTemplate.put(url + "/update", score, Score.class);
    }
}