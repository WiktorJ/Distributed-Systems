package game;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by wiktor on 20/03/16.
 */
public class PlayerListener implements IPlayerListener {

    private IBoard board;
    private IUserInterface userInterface;

    public PlayerListener(IBoard board, IUserInterface userInterface) {
        this.board = board;
        this.userInterface = userInterface;
    }

    @Override
    public BoardCell getPlayerMove() throws IOException {
        return userInterface.getUserMove();
    }

    @Override
    public void updatePlayerBoard(BoardCell cell, BoardFigure figure) {
        board.update(cell, figure);
    }

    @Override
    public void announceWinner() {
        System.out.println("Game is over, you are the winner!");
        board.clean();
    }

    @Override
    public void announceLooser() {
        System.out.println("Game is over, you lost");
        board.clean();
    }

    @Override
    public void announceDraw() {
        System.out.println("Game is over, it's draw!");
        board.clean();
    }

    @Override
    public void setBoardSize(int boardSize) throws RemoteException {
        board.setBoardSize(boardSize);
    }


}
