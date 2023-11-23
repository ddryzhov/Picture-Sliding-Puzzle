package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Score;

import java.util.Collections;
import java.util.List;

@Service
//@Primary
@Transactional
public class ScoreServiceJPA implements ScoreInterface{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }


    @Override
    public List<Score> getAllScores() {
        String query = "SELECT s FROM Score s ORDER BY s.moves DESC";
        TypedQuery<Score> typedQuery = entityManager.createQuery(query, Score.class);
        typedQuery.setMaxResults(5);
        return typedQuery.getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("delete from score").executeUpdate();
    }

    @Override
    public List<Score> findUserById(int id) throws ScoreException {
        return Collections.singletonList(entityManager.find(Score.class, id));
    }

    @Override
    public void update(Score score) {
        entityManager.merge(score);
    }
}