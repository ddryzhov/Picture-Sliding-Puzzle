package sk.tuke.gamestudio.game;

import org.springframework.stereotype.Service;

@Service
public class Move {
    private int countMoves;
    private Board board;
    public Move(Board board) {
        this.board = board;
        this.countMoves = 0;
    }

    public int getCountMoves() {
        return countMoves;
    }

    public void setCountMoves(int countMoves) {
        this.countMoves = countMoves;
    }

    //takes a String direction as input and makes a move in that direction by swapping the empty cell with the cell adjacent to it in the specified direction. It updates the count of moves made and the empty cell coordinates in the board accordingly.
    public void makeMove(String direction) {
        int newLine = board.getEmptyRow();
        int newColumn = board.getEmptyCol();

        if (direction.equals("w")) {
            newLine++;
            if(isValidMove(direction)) {
                countMoves++;
            }
        } else if (direction.equals("s")) {
            newLine--;
            if(isValidMove(direction)) {
                countMoves++;
            }
        } else if (direction.equals("a")) {
            newColumn++;
            if(isValidMove(direction)) {
                countMoves++;
            }
        } else if (direction.equals("d")) {
            newColumn--;
            if(isValidMove(direction)) {
                countMoves++;
            }
        }
        board.getBoard()[board.getEmptyRow()][board.getEmptyCol()] = board.getBoard()[newLine][newColumn];
        board.getBoard()[newLine][newColumn] = -1;
        board.setEmptyRow(newLine);
        board.setEmptyCol(newColumn);
    }

    //takes a String direction as input and checks if a move in that direction is valid. It does this by checking if the adjacent cell in the specified direction is within the boundaries of the board.
    public boolean isValidMove(String direction) {
        int newLine = board.getEmptyRow();
        int newColumn = board.getEmptyCol();

        if (direction.equals("w")) {
            newLine++;
        } else if (direction.equals("s")) {
            newLine--;
        } else if (direction.equals("a")) {
            newColumn++;
        } else if (direction.equals("d")) {
            newColumn--;
        } else {
            return false;
        }
        if (newLine >= 0 && newLine < board.getSize() && newColumn >= 0 && newColumn < board.getSize()) {
            return true;
        } else {
            return false;
        }
    }
}