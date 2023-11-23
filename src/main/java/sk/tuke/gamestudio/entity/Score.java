package sk.tuke.gamestudio.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @SequenceGenerator(
            name = "score_seq",
            sequenceName = "score_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_seq"
    )
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "moves")
    private int moves;

    @Column(name = "date")
    private Date date;

    public Score(String name, int moves, Date date) {
        this.name = name;
        this.moves = moves;
        this.date = date;
    }

    public Score() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", moves=" + moves +
                ", date=" + date +
                '}';
    }
}