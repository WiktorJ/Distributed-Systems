package game;

import exceptions.GameUnavailableToJoinException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by wiktor on 20/03/16.
 */
//TODO: Test on distributed environment
//TODO: Graphic interface
public class Client {
    public static void main(String[] args) throws IOException, NotBoundException, InterruptedException {
        if (args.length != 3) {
            System.out.println("Client <ip> <port> <nick>");
            return;
        }
        String nick = "player";
        System.out.println("G - get games, C - createGames, J - joinGame, B - game with bot, E - exit");
        IConnector connector = (IConnector) Naming.lookup("rmi://localhost:1099/connect");
        IBoard board = new ConsoleBoard();
        IUserInterface userInterface = new ConsoleUserInterface();
        IPlayerListener pl = new PlayerListener(board, userInterface);
        IPlayerListener playerListener = (IPlayerListener) UnicastRemoteObject.exportObject(pl, 0);

        GameSetup gameSetup;
        IPlayer player;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && !s.equals("E")) {
            switch (s) {
                case "G":
                    connector.getGames().stream().forEach(System.out::println);
                    break;
                case "B":
                    gameSetup = setupGame();
                    playerListener.setBoardSize(gameSetup.getBoardSize());
                    player = connector.createGameWithBot(nick, gameSetup, playerListener);
                    System.out.println("Your figure is: " + player.getFigure());
                    new LocalPlayer(player).run();
                    break;
                case "C":
                    gameSetup = setupGame();
                    playerListener.setBoardSize(gameSetup.getBoardSize());
                    player = connector.createGameWithRealOpponent(nick, gameSetup, playerListener);
                    System.out.println("Your figure is: " + player.getFigure());
                    new LocalPlayer(player).run();
                    break;
                case "J":
                    connector.getGames().stream().forEach(System.out::println);
                    System.out.print("type game id: ");
                    String id = in.readLine();
                    try {
                        player = connector.joinGame(Long.parseLong(id), nick, playerListener);
                        System.out.println("Your figure is: " + player.getFigure());
                        new LocalPlayer(player).run();
                    } catch (GameUnavailableToJoinException e) {
                        System.out.println("Cannot join game with id: " + id);
                    }
                    break;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private static GameSetup setupGame() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("1: Custom game setup\n2: Default game setup");
        s = in.readLine();
        GameSetup.GameSetupBuilder gameSetupBuilder = new GameSetup.GameSetupBuilder().boardSize(3).winCondition(3).title("default title");
        if (s.equals("2")) {
            return gameSetupBuilder.build();
        } else {
            System.out.println("\n1: Title\n2: Figure (CROSS/CIRCLE)\n3: Board size\n4: Win condition\n0: Finish");
            while ((s = in.readLine()) != null && !s.equals("E")) {
                System.out.println();
                try {
                    switch (s) {
                        case "1":
                            System.out.print("Title: ");
                            s = in.readLine();
                            gameSetupBuilder = gameSetupBuilder.title(s);
                            break;
                        case "2":
                            System.out.print("Figure: ");
                            s = in.readLine();
                            gameSetupBuilder = gameSetupBuilder.figure(BoardFigure.valueOf(s));
                            break;
                        case "3":
                            System.out.print("Board size: ");
                            s = in.readLine();
                            gameSetupBuilder = gameSetupBuilder.boardSize(Integer.parseInt(s));
                            break;
                        case "4":
                            System.out.print("Win condition: ");
                            s = in.readLine();
                            gameSetupBuilder = gameSetupBuilder.winCondition(Integer.parseInt(s));
                            break;
                        case "0":
                            return gameSetupBuilder.build();
                        default:
                            System.out.println("Wrong command");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Pleas provide correct input");
                }
            }
        }
        return gameSetupBuilder.build();
    }
}
