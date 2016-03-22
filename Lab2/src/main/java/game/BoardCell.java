package game;

import java.io.Serializable;

/**
 * Created by wiktor on 20/03/16.
 */
public class BoardCell implements Serializable, Comparable{
    private int row;
    private int column;

    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardCell boardCell = (BoardCell) o;

        if (row != boardCell.row) return false;
        return column == boardCell.column;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;

        BoardCell boardCell = (BoardCell) o;


        if (row < boardCell.row) return -1;
        if (row > boardCell.row) return 1;

        if (column < boardCell.column) return -1;
        if (column > boardCell.column) return 1;
        return 0;
    }
}
