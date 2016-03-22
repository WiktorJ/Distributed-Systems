package game;

import exceptions.IllegalMoveException;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 19/03/16.
 */
public interface IPlayer extends Serializable {
    boolean requestMove() throws IOException, IllegalMoveException, InterruptedException;
    BoardFigure getFigure();
}
