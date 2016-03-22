package game;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by wiktor on 20/03/16.
 */
public class ConsoleBoard implements IBoard {
    private BoardFigure[][] board;

    public ConsoleBoard() {
        int boardSize = 3;
        setBoardSize(boardSize);
    }

    @Override
    public void update(BoardCell cell, BoardFigure figure) {
        board[cell.getRow()][cell.getColumn()] = figure;
        for (BoardFigure[] boardFigures : board) {
            System.out.println();
            for (BoardFigure boardFigure : boardFigures) {
                System.out.print(boardFigure.getS() + " ");
            }
        }
        System.out.println();
    }

    @Override
    public void clean() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = BoardFigure.EMPTY;
            }
        }
    }

    @Override
    public void setBoardSize(int boardSize) {
        board = new BoardFigure[boardSize][boardSize];
        for (BoardFigure[] boardFigures : board) {
            Arrays.fill(boardFigures, BoardFigure.EMPTY);
        }
    }
}
