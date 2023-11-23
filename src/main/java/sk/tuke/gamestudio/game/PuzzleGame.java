package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.*;

import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

@Component
public class PuzzleGame extends Colors {
    @Autowired
    private CommInterface commInterface;
    @Autowired
    private ScoreInterface scoreInterface;

    @Autowired
    private RatingInterface ratingInterface;

    private Board board;
    private Move move;


    public PuzzleGame(Board board, Move move) {
        this.board = board;
        this.move = move;
    }

    //is called to start the game. It takes input from the user for the direction of the move, validates the move using the isValidMove() function of the Move object and makes the move using the makeMove() function of the Move object. It also checks if the game is solved by calling the isSolved() function.
    public void play() throws CommentException, ScoreException, RatingException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String userName = scanner.nextLine();
        while (isSolved() == false) {
            System.out.println("Number of moves made: " + STARTPAINT + move.getCountMoves());
            makeBoard();
            System.out.print("Write direction (w(up), s(down), a(left), d(right): ");
            String direction = scanner.nextLine();
            if (move.isValidMove(direction) == false) {
                System.out.println("Invalid move");
            } else {
                move.makeMove(direction);
            }
        }
        makeBoard();
        System.out.println("Congratulations, you solved the puzzle!");
        scoreInterface.addScore(new Score(userName,move.getCountMoves(), new Date()));

        System.out.println("Write any comment: ");
        String comment = scanner.nextLine();
        commInterface.addComment(new Comment(userName, comment, new Date()));

        System.out.println("Rate the game from 1 to 10: ");
        int rating = scanner.nextInt();
        if(rating <= 10 && rating >= 1) {
            ratingInterface.addRating(new Rating(userName, new Date(), rating));
        }
        else{
            throw new RatingException("The evaluation of the game is out of bounds");
        }
        //scoreInterface.reset();
        System.out.println(scoreInterface.getAllScores());
        System.exit(0);
        return;
    }

    //is used to print the current state of the board on the console. It uses the board object to get the current state of the board and prints it using some formatting.
    public void makeBoard() {
        String horizontalBorder = STARTPAINT + "*" + ENDPAINT + String.join(STARTPAINT+ "*" + ENDPAINT, Collections.nCopies(board.getSize(), STARTPAINT + "---" + ENDPAINT)) + STARTPAINT + "*" + ENDPAINT;

        for (int i = 0; i < board.getBoard().length; i++) {
            System.out.println(horizontalBorder);
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (board.getBoard()[i][j] == -1) {
                    System.out.print(STARTPAINT + "|   " + ENDPAINT);
                } else {
                    System.out.printf(STARTPAINT + "| " + ENDPAINT + STARTPAINT2 + "%2d" + ENDPAINT, board.getBoard()[i][j]);
                }
            }
            System.out.println(STARTPAINT + "|" + ENDPAINT);
        }
        System.out.println(horizontalBorder);
    }

    //checks if the game is solved or not.
    public boolean isSolved() {
        int count = 1;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard() [i].length; j++) {
                if (board.getBoard() [i][j] == count) {
                    count++;
                } else if (board.getBoard()[i][j] != -1) {
                    return false;
                }
            }
        }
        if (board.getBoard() [board.getSize()-1][board.getSize()-1] == -1) {
            return true;
        }
        return false;
    }
}