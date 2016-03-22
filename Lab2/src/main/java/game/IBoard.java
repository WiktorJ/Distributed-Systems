package game;

/**
 * Created by wiktor on 20/03/16.
 */
public interface IBoard {
    void update(BoardCell cell, BoardFigure figure);
    void clean();
    void setBoardSize(int boardSize);
}
