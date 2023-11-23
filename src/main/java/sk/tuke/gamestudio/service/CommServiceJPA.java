package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

@Service
@Transactional
public class CommServiceJPA implements CommInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getAllComments() throws CommentException {
        String query = "SELECT s FROM Comment s ORDER BY s.content ASC";
        TypedQuery<Comment> typedQuery = entityManager.createQuery(query, Comment.class);
        return typedQuery.getResultList();
    }
}