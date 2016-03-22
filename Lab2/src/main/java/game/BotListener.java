package game;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by wiktor on 22/03/16.
 */
public class BotListener implements IPlayerListener, Serializable {

    private List<BoardCell> movesToChoose;

    public BotListener() throws RemoteException {
        movesToChoose = new LinkedList<>();
        setBoardSize(3);
    }

    @Override
    public BoardCell getPlayerMove() throws RemoteException, IOException {
        Optional<BoardCell> any = movesToChoose.stream().findAny();
        movesToChoose.remove(any.get());
        return any.get();
    }

    @Override
    public void updatePlayerBoard(BoardCell cell, BoardFigure figure) throws RemoteException {
        movesToChoose.remove(cell);
    }

    @Override
    public void announceWinner() throws RemoteException {
    }

    @Override
    public void announceLooser() throws RemoteException {

    }

    @Override
    public void announceDraw() throws RemoteException {

    }

    @Override
    public void setBoardSize(int boardSize) throws RemoteException {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                movesToChoose.add(new BoardCell(i, j));
            }
        }
        Collections.shuffle(movesToChoose);
    }
}
