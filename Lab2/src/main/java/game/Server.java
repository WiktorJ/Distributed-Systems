package game;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by wiktor on 20/03/16.
 */
public class Server {
    static IConnector c;
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        if (args.length != 2) {
            System.out.println("Server <ip> <port>");
            return;
        }
        c = new Connector();
        IConnector connector = (IConnector) UnicastRemoteObject.exportObject(c, 0);
        Naming.rebind("rmi://" + args[0] + ":" + args[1] + "/connect", connector);
    }
}
