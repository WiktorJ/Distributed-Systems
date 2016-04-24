package server;

import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.UserException;
import impl.Counter;

/**
 * Created by wiktor on 24/04/16.
 */
public class PFourLocator extends AbstractLocator {

    private Counter servant;

    public PFourLocator(String id) {
        super(id);
        servant = new Counter();
        System.out.println("## PTwoLocator(" + id + ") ##");
    }


    @Override
    public Object locate(Current current, LocalObjectHolder localObjectHolder) throws UserException {
        return servant;
    }

}
