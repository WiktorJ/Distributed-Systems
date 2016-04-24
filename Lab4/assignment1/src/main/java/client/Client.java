package client;

import Ice.AsyncResult;
import assignment1.CounterPrx;
import assignment1.CounterPrxHelper;

/**
 * Created by wiktor on 24/04/16.
 */
public class Client {

    public static void main(String[] args)
    {
        int status = 0;
        Ice.Communicator communicator = null;

        try {
            communicator = Ice.Util.initialize(args);
//            Ice.ObjectPrx base1 = communicator.stringToProxy("c1/o1:tcp -h localhost -p 10000:udp -h localhost -p 10000:ssl -h localhost -p 10001");
            Ice.ObjectPrx base1 = communicator.propertyToProxy("Counter1.Proxy");

            CounterPrx calc1 = CounterPrxHelper.checkedCast(base1);
            if (calc1 == null) throw new Error("Invalid proxy");

            String line = null;
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

            do
            {
                try
                {
                    System.out.print("==> ");
                    System.out.flush();
                    line = in.readLine();

                    if (line == null)
                    {
                        break;
                    }
                    if (line.equals("dec"))
                    {
                        float r = calc1.decrement();
                        System.out.println("RESULT (syn) = " + r);
                    }
                    if (line.equals("inc"))
                    {
                        float r = calc1.increment();
                        System.out.println("RESULT (syn) = " + r);
                    }
                }
                catch (java.io.IOException ex)
                {
                    System.err.println(ex);
                }
            }
            while (!line.equals("x"));


        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (communicator != null) {
            // Clean up
            //
            try {
                communicator.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }

}

