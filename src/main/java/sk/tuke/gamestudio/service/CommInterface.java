package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

@Repository
public interface CommInterface {
    void addComment(Comment comment) throws CommentException;
    public List<Comment> getAllComments() throws CommentException;
}