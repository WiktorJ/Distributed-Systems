package server;

import FinancialNews.FinancialNewsReceiverPrx;
import FinancialNews.FinancialNewsReceiverPrxHelper;
import FinancialNews.FinancialNewsServerPrx;
import FinancialNews.FinancialNewsServerPrxHelper;
import Ice.ObjectAdapter;
import impl.FinancialNewsReceiver;

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
            ObjectAdapter adapter = communicator.createObjectAdapter("Bank");
            adapter.addServantLocator(new BankServantEvictor(2), "customer");
            adapter.activate();


            FinancialNewsServerPrx serverProxy = FinancialNewsServerPrxHelper.uncheckedCast(
                    communicator.propertyToProxy("FinancialNews").ice_twoway()
            );

            ObjectAdapter notifierAdapter = communicator.createObjectAdapter("");
            FinancialNewsReceiver financialNewsReceiver = new FinancialNewsReceiver();
            FinancialNewsReceiverPrx cprx = FinancialNewsReceiverPrxHelper.uncheckedCast(notifierAdapter.add(financialNewsReceiver, new Ice.Identity(java.util.UUID.randomUUID().toString(), "")));
            notifierAdapter.activate();
            serverProxy.ice_getConnection().setAdapter(notifierAdapter);
            serverProxy.registerForNews(cprx);
            System.out.println("Entering event processing loop...");

            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        financialNewsReceiver.ice_ping();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
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
