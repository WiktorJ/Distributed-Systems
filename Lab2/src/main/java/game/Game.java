package game;

import exceptions.IllegalMoveException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wiktor on 19/03/16.
 */
public class Game implements IGame{

    private int boardSize;
    private int winCondition;
    private Long id;
    private String title;
    private BoardFigure[][] board;
    private volatile BoardFigure currentMove;
    private Lock lock;
    private Condition moveCondition;
    private Map<BoardFigure, IPlayerListener> playerListenerMap;
    private int moveCounter = boardSize * boardSize;
    private volatile boolean gameFinished = false;

    public Game(Long id, String title, int boardSize, int winCondition) {
        this.id = id;
        this.title = title;
        this.boardSize = boardSize;
        this.winCondition = winCondition;
        board = new BoardFigure[this.boardSize][this.boardSize];
        for (BoardFigure[] boardFigures : board) {
            Arrays.fill(boardFigures, BoardFigure.EMPTY);
        }
        currentMove = BoardFigure.EMPTY;
        lock = new ReentrantLock();
        moveCondition = lock.newCondition();
        playerListenerMap = new HashMap<>();
    }

    public boolean  makeMove(final BoardFigure figure) throws IllegalMoveException, InterruptedException, IOException {
        lock.lock();
        try {
            while (!figure.equals(currentMove) && !gameFinished) {
                moveCondition.await();
            }
            if (gameFinished) {
                moveCondition.signal();
                return true;
            }

            BoardCell playerMove;
            boolean flag = false;
            do {
                playerMove = playerListenerMap.get(figure).getPlayerMove();
                if (playerMove.getColumn() < 0 || playerMove.getRow() < 0 || playerMove.getColumn() > boardSize - 1
                        || playerMove.getRow() > boardSize - 1 || !board[playerMove.getRow()][playerMove.getColumn()].equals(BoardFigure.EMPTY)) {
                    System.out.println(("Cannot make move on: " + playerMove.getColumn() + ":" + playerMove.getRow()));
                } else {
                    flag = true;
                }
            } while (!flag);

            int column = playerMove.getColumn();
            int row = playerMove.getRow();

            board[row][column] = figure;

            for (IPlayerListener playerListener : playerListenerMap.values()) {
                playerListener.updatePlayerBoard(playerMove, figure);
            }

            moveCounter--;
            if (checkIfVictoriousMove(row, column, figure)) {
                playerListenerMap.get(figure).announceWinner();
                playerListenerMap.get(BoardFigure.getOpposite(figure)).announceLooser();
                gameFinished = true;
                moveCondition.signal();
                return true;
            } else {
                if (moveCounter == 0) {
                    playerListenerMap.values().stream().forEach((listener) -> {
                        try {
                            listener.announceDraw();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    });
                    gameFinished = true;
                    moveCondition.signal();
                    return true;
                }
                moveCondition.signal();
                return false;
            }
        } finally {
            currentMove = BoardFigure.getOpposite(figure);
            lock.unlock();
        }
    }

    private boolean checkIfVictoriousMove(final int row, final int column, final BoardFigure figure) {
        int counter = 0;
        for (BoardFigure boardFigure : board[row]) {
            if (boardFigure.equals(figure)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == winCondition) {
                return true;
            }
        }

        counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][column].equals(figure)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == winCondition) {
                return true;
            }
        }

        int stepsToLeftUp = Math.min(row, column);
        int i = row - stepsToLeftUp;
        int j = column - stepsToLeftUp;
        counter = 0;
        while (i < boardSize && j < boardSize) {
            if (board[i][j].equals(figure)) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == winCondition) {
                return true;
            }
            j++;
            i++;
        }
        i = row + Math.min(boardSize - row - 1, column);
        j = column - Math.min(boardSize - row - 1, column);
        counter = 0;
        while (i >= 0 && j < boardSize) {
            if (board[i][j].equals(figure)) {
                counter++;
            } else {
                counter = 0;
            }

            if (counter == winCondition) {
                return true;
            }
            j++;
            i--;
        }
        return false;
    }

    public void addPlayerListener(BoardFigure figure, IPlayerListener listener) {
        playerListenerMap.put(figure, listener);
    }

    public void startGame() {
        lock.lock();
        try {
            currentMove = BoardFigure.CROSS;
            moveCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getBoardSize() {
        return boardSize;
    }
}

