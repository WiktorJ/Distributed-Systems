package game;

import com.sun.org.apache.regexp.internal.RE;
import exceptions.IllegalMoveException;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 20/03/16.
 */
public interface IGame extends Remote{
    boolean makeMove(final BoardFigure figure) throws IllegalMoveException, InterruptedException, IOException, RemoteException;

    void addPlayerListener(BoardFigure figure, IPlayerListener listener) throws RemoteException;

    void startGame() throws RemoteException;

    public int getBoardSize() throws RemoteException;

}
