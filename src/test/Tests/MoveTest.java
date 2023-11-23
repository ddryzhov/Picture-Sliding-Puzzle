package Tests;

import Game.Board;
import Game.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {
    private Board board;
    private Move move;


    @BeforeEach
    void setUp() {
        board = new Board(3);
        move = new Move(board);
    }
    @Test
    public void testMakeMove() {
        //  1  2  3
        //  4  5  6
        //  7  8 -1
        int[][] state = {{1, 2, 3}, {4, 5, 6}, {7, 8, -1}};
        board.setBoard(state);

        move.makeMove("s");
        int[][] expectedState = {{1, 2, 3}, {4, 5, -1}, {7, 8, 6}};
        assertArrayEquals(expectedState, board.getBoard());
        assertEquals(1, move.getCountMoves());
        }

    @Test
    void testIsValidMove() {
        Assertions.assertFalse(move.isValidMove("w"));
        Assertions.assertTrue(move.isValidMove("s"));
        Assertions.assertFalse(move.isValidMove("a"));
        Assertions.assertTrue(move.isValidMove("d"));
        Assertions.assertFalse(move.isValidMove("x"));
    }
}