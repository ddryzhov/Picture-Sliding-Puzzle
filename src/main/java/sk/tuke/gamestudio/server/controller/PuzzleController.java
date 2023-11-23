package sk.tuke.gamestudio.server.controller;

import com.sun.jdi.PrimitiveValue;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Board;
import sk.tuke.gamestudio.game.Move;
import sk.tuke.gamestudio.game.PuzzleGame;
import sk.tuke.gamestudio.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
    private Score score;
    private Rating rating;

    @Autowired
    private CommInterface commInterface;

    @Autowired
    @Qualifier("scoreServiceREST")
    private ScoreInterface scoreInterface;

    @Autowired
    private RatingInterface ratingInterface;

    RestTemplate restTemplate = new RestTemplate();
    Board board = new Board(3);
    Move move = new Move(board);
    PuzzleGame puzzleGame = new PuzzleGame(board,move);

    @RequestMapping("/puzzle")
    public String puzzle()
    {
        if(score.getName().equals(""))
        {
            score.setName("guess");
        }
        return "puzzle";
    }

    public String getPuzzleBoard() {
            StringBuilder sb = new StringBuilder();
            sb.append("<table style=\"border-collapse: collapse;\">\n");
            for (int i = 0; i < board.getSize(); i++) {
                sb.append("<tr>\n");
                for (int j = 0; j < board.getSize(); j++) {
                        sb.append("<td style=\"border: 4px solid #2f3640; width: 133px; height: 125px;\">");
                    if (board.board[i][j] == -1) {
                        sb.append("&nbsp;");
                    } else {
                        sb.append(board.board[i][j]);
                    }
                    sb.append("</td>\n");
                }
                sb.append("</tr>\n");
            }
            sb.append("</table>\n");
            return sb.toString();
    }

    @RequestMapping("/puzzle/move-right")
    public String moveNumberRight(Model model) throws ScoreException {
        if (!puzzleGame.isSolved() && isValidWeb("d").equals("yes")) {
            move.makeMove("d");
        }
        else if (isValidWeb("d").equals("no")) {
            model.addAttribute("message", "It is impossible to take a step in this direction.");
            return "puzzle :: #try";
        }
        if (puzzleGame.isSolved()) {
            handleWin(model, move, score);
            restTemplate.put("http://localhost:8080/api/score/update", score);
            return "puzzle :: #win";
        }
        model.addAttribute("board", getPuzzleBoard());
        return "puzzle :: #puzzle-container";
    }

    @RequestMapping("/puzzle/move-left")
    public String moveNumberLeft(Model model) throws ScoreException {
        if (!puzzleGame.isSolved() && isValidWeb("a").equals("yes")) {
            move.makeMove("a");
        }
        else if (isValidWeb("a").equals("no")) {
            model.addAttribute("message", "It is impossible to take a step in this direction.");
            return "puzzle :: #try";
        }
        if (puzzleGame.isSolved()) {
            handleWin(model, move, score);
            restTemplate.put("http://localhost:8080/api/score/update", score);
            return "puzzle :: #win";
        }
        model.addAttribute("board", getPuzzleBoard());
        return "puzzle :: #puzzle-container";
    }

    @RequestMapping("/puzzle/move-up")
    public String moveNumberUP(Model model) throws ScoreException {
        if (!puzzleGame.isSolved() && isValidWeb("w").equals("yes")) {
            move.makeMove("w");

        }
        else if (isValidWeb("w").equals("no")) {
            model.addAttribute("message", "It is impossible to take a step in this direction.");
            return "puzzle :: #try";
        }
        if (puzzleGame.isSolved()) {
            handleWin(model, move, score);
            restTemplate.put("http://localhost:8080/api/score/update", score);
            return "puzzle :: #win";
        }
        model.addAttribute("board", getPuzzleBoard());
        return "puzzle :: #puzzle-container";
    }

    @RequestMapping("/puzzle/move-down")
    public String moveNumberDown(Model model) throws ScoreException {
        if (!puzzleGame.isSolved() && isValidWeb("s").equals("yes")) {
            move.makeMove("s");

        }
        else if (isValidWeb("s").equals("no")) {
            model.addAttribute("message", "It is impossible to take a step in this direction.");
            return "puzzle :: #try";
        }
        if (puzzleGame.isSolved()) {
            handleWin(model, move, score);
            restTemplate.put("http://localhost:8080/api/score/update", score);
            return "puzzle :: #win";
        }
        model.addAttribute("board", getPuzzleBoard());
        return "puzzle :: #puzzle-container";
    }

    public void handleWin(Model model, Move move, Score score) {
        model.addAttribute("message", "You won!");
        score.setMoves(move.getCountMoves());
        score.setDate(new Date());
    }

    @RequestMapping("/puzzle/step")
    @ResponseBody
    public int step(){
        return move.getCountMoves();
    }

    @RequestMapping("/puzzle/valid")
    @ResponseBody
    public String isValidWeb(String direction){
        if(move.isValidMove(direction)){
            return "yes";
        }
        return "no";
    }

    @GetMapping("/puzzle/comment")
    public String comment(Model model) throws CommentException {
        List<Comment> comments = commInterface.getAllComments();
        model.addAttribute("comments", comments);
        return "puzzle::container1";
    }

    @PostMapping("/puzzle/comment")
    public String commentPost(@ModelAttribute Comment comment) throws CommentException {
        comment.setDate(new Date());
        comment.setUsername(score.getName());
        commInterface.addComment(comment);
        return "redirect:/puzzle";
    }

    @GetMapping("/puzzle/rating")
    public String rating(Model model) throws RatingException {
        List<Rating> ratings = ratingInterface.getAllRating();
        model.addAttribute("comments", ratings);
        return "puzzle::container2";
    }

    @PostMapping("/puzzle/rating")
    public String ratingPost(@ModelAttribute Rating rating) throws  RatingException {
        if(rating.getRating() > 5 || rating.getRating() < 1){
            return "puzzle";
        }
        else
        {
            rating.setDate(new Date());
            rating.setUsername(score.getName());
            ratingInterface.addRating(rating);
        }
        return "redirect:/puzzle";
    }

    @GetMapping("puzzle/guessPlayer")
    @ResponseBody
    public String guess(Model model)
    {
        return score.getName();
    }

    @GetMapping("/login")
    public String login(Model model){
        Score score = new Score();
        model.addAttribute("score", score);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute Score score) throws ScoreException {
        List<Score> players = scoreInterface.getAllScores();
        List<String> usernames = new ArrayList<>();

        for(Score p : players) {
            usernames.add(p.getName());
        }

        if(score.getName().equals(""))
        {
            this.score = score;
            return "redirect:/puzzle";
        }
        if(score.getName().length() < 5 || !usernames.contains(score.getName())){
            return "login";
        }
        this.score = score;
        return "redirect:/puzzle";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        Score score = new Score();
        model.addAttribute("score", score);
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute Score player, Model model) throws ScoreException
    {
        List<Score> players = scoreInterface.getAllScores();
        List<String> usernames = new ArrayList<>();
        for(Score p : players) {
            usernames.add(p.getName());

        }
        if(usernames.contains(player.getName()))
        {
            model.addAttribute("error","Incorrect");
            return "redirect:/register";
        }

        this.score = player;
        this.score.setName(this.score.getName());
        restTemplate.put("http://localhost:8080/api/score/update", this.score);
        return "redirect:/login";
    }

    @PostMapping("/puzzle/ratingTable")
    public String ratingPostTop(@ModelAttribute Rating rating){
        this.rating = rating;
        return "redirect:/puzzle";
    }

    @GetMapping("/puzzle/ratingTable")
    @ResponseBody
    public List<Rating> ratingTop() throws RatingException {
        List<Rating> ratings = ratingInterface.getAllRating();
        return ratings;
    }

    @RequestMapping("/puzzle/toprating")
    @ResponseBody
    public List<Score> scoreTop() throws ScoreException {
        return scoreInterface.getAllScores();
    }

    @RequestMapping("/new")
    public String newGame() {
        board = new Board(3);
        move = new Move(board);
        puzzleGame = new PuzzleGame(board, move);

        return "redirect:/puzzle";
    }

    @RequestMapping("/K/middle-rating")
    @ResponseBody
    public List<Rating> getTopRating() throws RatingException {
        return ratingInterface.getAllRating();
    }
}