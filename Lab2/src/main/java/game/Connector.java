package game;

import exceptions.GameUnavailableToJoinException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wiktor on 19/03/16.
 */
public class Connector implements IConnector {

    private static AtomicLong idCounter = new AtomicLong(0);
    private Map<Long, GameDescription> gameDescriptionMap;
    private Map<Long, IGame> games;
    private Lock gameMapLock;


    public Connector() {
        gameDescriptionMap = new HashMap<>();
        games = new HashMap<>();
        gameMapLock = new ReentrantLock();
    }


    public ArrayList<GameDescription> getGames() throws RemoteException {
        return new ArrayList<>(gameDescriptionMap.values());
    }

    public IPlayer joinGame(long id, String nick, IPlayerListener listener) throws RemoteException, GameUnavailableToJoinException {
        IGame game = null;
        GameDescription gameDescription = null;
        gameMapLock.lock();
        try {
            if(!games.containsKey(id)) {
                throw new GameUnavailableToJoinException("There is no game with such id: " + id + " or the game has already started");
            }
            game = games.remove(id);
            gameDescription = gameDescriptionMap.remove(id);
        } finally {
            gameMapLock.unlock();
        }
        game.addPlayerListener(BoardFigure.getOpposite(gameDescription.getCreatorFigure()), listener);
        game.startGame();
        return new Player(nick, game, BoardFigure.getOpposite(gameDescription.getCreatorFigure()));
    }


    public IPlayer createGameWithRealOpponent(String nick, GameSetup gameSetup, IPlayerListener listener) throws RemoteException {
        long id = getUniqueId();
        IGame g = new Game(id, gameSetup.getTitle(), gameSetup.getBoardSize(), gameSetup.getWinCondition());
        IGame game = (IGame) UnicastRemoteObject.exportObject(g, 0);
        game.addPlayerListener(gameSetup.getCreatorFigure(), listener);
        Player player = new Player(nick, game, gameSetup.getCreatorFigure());
        gameMapLock.lock();
        try {
            games.put(id, game);
            gameDescriptionMap.put(id, new GameDescription(id, nick, gameSetup.getTitle(), gameSetup.getCreatorFigure()));
        } finally {
            gameMapLock.unlock();
        }
        return player;
    }

    public IPlayer createGameWithBot(String nick, GameSetup gameSetup, IPlayerListener listener) throws RemoteException {
        long id = getUniqueId();
        IGame g = new Game(id, gameSetup.getTitle(), gameSetup.getBoardSize(), gameSetup.getWinCondition());
        IGame game = (IGame) UnicastRemoteObject.exportObject(g, 0);
        game.addPlayerListener(gameSetup.getCreatorFigure(), listener);
        BotListener botListener = new BotListener();
        botListener.setBoardSize(gameSetup.getBoardSize());
        game.addPlayerListener(BoardFigure.getOpposite(gameSetup.getCreatorFigure()), botListener);
        game.startGame();
        new Thread(new LocalPlayer(new Player("bot", game, BoardFigure.getOpposite(gameSetup.getCreatorFigure())))).start();
        return new Player(nick, game, gameSetup.getCreatorFigure());
    }

    private static Long getUniqueId() {
        return idCounter.getAndIncrement();
    }

}
