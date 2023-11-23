package sk.tuke.gamestudio.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;


@Service
@Transactional
public class RatingServiceJPA implements RatingInterface{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRating(Rating rating) throws RatingException {
        entityManager.persist(rating);
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNativeQuery("delete from rating").executeUpdate();
    }

    @Override
    public List<Rating> getAllRating() throws RatingException {
        String query = "SELECT s FROM Rating s ORDER BY s.rating DESC";
        TypedQuery<Rating> typedQuery = entityManager.createQuery(query, Rating.class);
        typedQuery.setMaxResults(5);
        return typedQuery.getResultList();
    }
}