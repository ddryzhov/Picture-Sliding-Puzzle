package sk.tuke.gamestudio.server.webservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingException;
import sk.tuke.gamestudio.service.RatingInterface;

import java.util.List;

@RestController
@RequestMapping("/ddi/rating")
public class RatingServiceRest implements RatingInterface {

    @Autowired
    RatingInterface ratingInterface;

    @PostMapping
    public void addRating(@RequestBody Rating rating) throws RatingException {
        ratingInterface.addRating(rating);
    }

    @GetMapping("/del")
    public void reset() throws RatingException {
        ratingInterface.reset();
    }

    @Override
    public List<Rating> getAllRating() throws RatingException {
        return ratingInterface.getAllRating();
    }
}