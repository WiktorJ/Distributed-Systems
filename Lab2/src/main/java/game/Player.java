package game;

import exceptions.IllegalMoveException;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 19/03/16.
 */
public class Player implements IPlayer {
    private String nick;
    private IGame game;
    private BoardFigure figure;

    public Player(String nick, IGame game, BoardFigure figure) {
        this.nick = nick;
        this.game = game;
        this.figure = figure;
    }

    @Override
    public boolean requestMove() throws IOException, IllegalMoveException, InterruptedException {
        return game.makeMove(figure);
    }

    @Override
    public BoardFigure getFigure() {
        return figure;
    }
}
