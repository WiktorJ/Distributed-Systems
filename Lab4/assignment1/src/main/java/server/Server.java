package server;

/**
 * Created by wiktor on 24/04/16.
 */
public class Server {

    public void t1(String[] args)
    {
        int status = 0;
        Ice.Communicator communicator = null;

        try
        {
            communicator = Ice.Util.initialize(args);
            Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1",
                    "tcp -h localhost -p 10000:udp -h localhost -p 10000");

            adapter.addServantLocator(new POneLocator("p1", adapter), "c1");
            adapter.addServantLocator(new PTwoLocator("p2"), "c2");

            adapter.activate();
            System.out.println("Entering event processing loop...");
            communicator.waitForShutdown();
        }
        catch (Exception e)
        {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null)
        {
            // Clean up
            try
            {
                communicator.destroy();
            }
            catch (Exception e)
            {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }


    public static void main(String[] args)
    {
        Server app = new Server();
        app.t1(args);
    }


}
