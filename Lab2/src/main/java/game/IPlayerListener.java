package game;

import java.awt.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 20/03/16.
 */
public interface IPlayerListener extends Remote{
    BoardCell getPlayerMove() throws RemoteException, IOException;
    void updatePlayerBoard(BoardCell cell, BoardFigure figure) throws RemoteException;
    void announceWinner() throws RemoteException;
    void announceLooser() throws RemoteException;
    void announceDraw() throws RemoteException;
    void setBoardSize(int boardSize) throws RemoteException;

}
