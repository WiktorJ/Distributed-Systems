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
            Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");


            //Leniwa incjalizacja. Aplikacj która pobiera propercje z zewnętrznego serwara może kożystać z takiego modelu.
            adapter.addServantLocator(new POneLocator("p1", adapter), "c1");
            //Bez stanowy lekki obiekt. Stworzenie obiektu który ma za zadanie wysłać request do innego modułu i zwrócić jego wynik.
            adapter.addServantLocator(new PTwoLocator("p2"), "c2");
            //Servanty ze współdzielonym zasobem. Workerzy stanowiący warstwę abstrakcji nad dostępem do bazy danych.
            adapter.addServantLocator(new PThreeLocator("p3", 2), "c3");
            //Bez stanowy moduł obliczeniowy. Globalny licznik id.
            adapter.addServantLocator(new PFourLocator("p4"), "c4");
            //Aplikacja wymagająca logowania która trzyma w pamięci  tylko najaktywniejszych zalogowanych uzytkowników.
            adapter.addServantLocator(new PFiveLocator("p5", 2), "c5");

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
