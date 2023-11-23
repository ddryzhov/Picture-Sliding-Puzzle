package sk.tuke.gamestudio.service;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;
@Repository
public interface ScoreInterface{
    public void addScore(Score score) throws ScoreException;

    public List<Score> getAllScores() throws ScoreException;
    public void reset() throws ScoreException;
    public List<Score>  findUserById(int id) throws ScoreException;
    void update(Score score);

}