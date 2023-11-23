package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @SequenceGenerator(
            name = "rating_seq",
            sequenceName = "rating_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_seq"
    )

    @Column(name = "id")
    private Long ratingId;

    @Column(name = "name")
    private String username;

    @Column(name = "date")
    private Date date;

    @Column(name = "rating")
    private int rating;

    public Rating(String username, Date date, int rating) {
        this.username = username;
        this.date = date;
        this.rating = rating;
    }

    public Rating() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getRatingId() {
        return ratingId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}