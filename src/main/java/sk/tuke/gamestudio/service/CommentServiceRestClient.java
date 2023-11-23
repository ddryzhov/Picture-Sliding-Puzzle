package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentServiceRestClient implements CommInterface{
    private final String url = "http://localhost:8080/kki/comment";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addComment(Comment comment) throws CommentException {
        restTemplate.postForEntity(url, comment, Comment.class);
    }

    @Override
    public List<Comment> getAllComments() throws CommentException {
        return Arrays.asList(restTemplate.getForEntity(url + "/" + "all", Comment[].class).getBody());
    }
}