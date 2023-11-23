//package Game;
//
//import JDBC.AddScore;
//import JDBC.Score;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Scanner;
//
//public class Play extends Colors{
//    public static void main(String[] args) throws SQLException {
//        Scanner myObj = new Scanner(System.in);
//        System.out.println("Enter username:");
//        String userName = myObj.nextLine();
//        Score scoreService = new Score();
//        AddScore addScore = new AddScore();
//
//        Board board = new Board(3);
//        Move move = new Move(board);
//        PuzzleGame puzzleGame = new PuzzleGame(board,move);
//        puzzleGame.play();
//        int moves = move.getCountMoves();
//        System.out.println("Number of moves made: " +  STARTPAINT + moves + ENDPAINT);
//
//        addScore.addScore(userName, moves);
//
//        List<String> topPlayers = scoreService.getTopPlayers();
//        System.out.println("Top Players:");
//        for (String player : topPlayers) {
//            System.out.println(player);
//        }
//        scoreService.close();
//    }
//}