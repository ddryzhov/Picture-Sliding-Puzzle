package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

@Repository
public interface RatingInterface {

    void addRating(Rating rating) throws RatingException;
    public void reset() throws RatingException;

    public  List<Rating> getAllRating() throws RatingException;
}