package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment{

    @Id
    @SequenceGenerator(
            name = "comment_seq",
            sequenceName = "comment_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_seq"
    )
    @Column(name = "id")
    private Long commentId;

    @Column(name = "name")
    private String username;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    public Comment(String username, String content, Date date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public Comment() {

    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}