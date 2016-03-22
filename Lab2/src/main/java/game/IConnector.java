package game;

import exceptions.GameUnavailableToJoinException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Created by wiktor on 19/03/16.
 */
public interface IConnector extends Remote{
    Collection<GameDescription> getGames() throws RemoteException;
    IPlayer joinGame(long id, String nick, IPlayerListener playerListener) throws RemoteException, GameUnavailableToJoinException;
    IPlayer createGameWithRealOpponent(String nick, GameSetup gameSetup, IPlayerListener listener) throws RemoteException;
    IPlayer createGameWithBot(String nick, GameSetup gameSetup, IPlayerListener listener) throws RemoteException;
}
