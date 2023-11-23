package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommInterface;
import sk.tuke.gamestudio.service.CommentException;

import java.util.List;

@RestController
@RequestMapping("/kki/comment")
public class CommentServiceRest implements CommInterface {
    @Autowired
    CommInterface commInterface;
    @PostMapping
    public void addComment(@RequestBody Comment comment) throws CommentException {
        commInterface.addComment(comment);
    }

    @GetMapping("/all")
    public List<Comment> getAllComments() throws CommentException {
        return commInterface.getAllComments();
    }
}