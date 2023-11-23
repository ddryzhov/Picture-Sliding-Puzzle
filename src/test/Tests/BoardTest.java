package Tests;

import Game.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(3);
    }

    @Test
    void testInitBoard() {
        int[][] expectedBoard = {{2, 4, 1}, {8, 3, 7}, {6, 5, -1}};

        board.setBoard(expectedBoard);

        Assertions.assertArrayEquals(expectedBoard, board.getBoard());
        Assertions.assertEquals(2, board.getEmptyRow());
        Assertions.assertEquals(2, board.getEmptyCol());
        Assertions.assertEquals(3, board.getSize());
    }
}