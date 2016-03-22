package game;

import exceptions.IllegalMoveException;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 20/03/16.
 */
public class LocalPlayer implements Runnable, Serializable {

    private IPlayer player;
    private volatile boolean gameFinished = false;

    public LocalPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            while (!player.requestMove()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
