package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingInterface{

    private final String url = "http://localhost:8080/ddi/rating";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addRating(Rating rating) throws RatingException {
        restTemplate.postForEntity(url, rating, Comment.class);
    }

    @Override
    public void reset() throws RatingException {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public List<Rating> getAllRating() throws RatingException {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + "all", Rating[].class).getBody());
    }
}