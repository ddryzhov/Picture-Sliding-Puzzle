package sk.tuke.gamestudio.game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Board {
    public int[][] board;
    private int emptyRow;
    private int emptyCol;
    private int index;
    private ArrayList<Integer> numbers;

    private int size;

    public Board(int size) {
        this.size = size;
        board = new int[size][size];
        initBoard();
    }

    //initializes the board by filling it with numbers from 1 to size * size - 1, except for the last cell which is left empty.
    private void initBoard() {
        index = 0;
        numbers = new ArrayList<>();

        for (int i = 1; i <= size * size - 1; i++) {
            numbers.add(i);
        }

//        Collections.shuffle(numbers);
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                if (index < numbers.size()) {
//                    board[i][j] = numbers.get(index);
//                    index++;
//                }
//            }
//        }
        board[0][0] = 2;
        board[0][1] = 4;
        board[0][2] = 1;
        board[1][0] = 8;
        board[1][1] = 3;
        board[1][2] = 7;
        board[2][0] = 6;
        board[2][1] = 5;


        emptyRow = size - 1;
        emptyCol = size - 1;
        board[emptyRow][emptyCol] = -1;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setEmptyRow(int emptyRow) {
        this.emptyRow = emptyRow;
    }

    public void setEmptyCol(int emptyCol) {
        this.emptyCol = emptyCol;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}