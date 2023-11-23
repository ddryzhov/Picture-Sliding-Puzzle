package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreException;
import sk.tuke.gamestudio.service.ScoreInterface;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceREST implements ScoreInterface{
    @Autowired
    ScoreInterface scoreInterface;

    @PostMapping("/login")
    public void addScore(@RequestBody Score score) throws ScoreException {
        scoreInterface.addScore(score);
    }

    @GetMapping("/game")
    public List<Score> getAllScores() throws ScoreException {
        return scoreInterface.getAllScores();
    }

    @GetMapping("/del")
    public void reset() throws ScoreException {
        scoreInterface.reset();
    }

    @GetMapping("/users/{id}")
    public List<Score> findUserById(@PathVariable int id) throws ScoreException {
        return scoreInterface.findUserById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Score score) {
        this.scoreInterface.update(score);
    }
}