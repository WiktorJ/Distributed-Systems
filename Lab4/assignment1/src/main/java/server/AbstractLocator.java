package server;

import Ice.UserException;

/**
 * Created by wiktor on 24/04/16.
 */
public abstract class AbstractLocator implements Ice.ServantLocator  {

    private String id;

    protected AbstractLocator(String id) {
        this.id = id;
    }

    @Override
    public void finished(Ice.Current curr, Ice.Object servant, java.lang.Object cookie) throws UserException {
        System.out.println("## ServantLocator1 #" + id + " .finished() ##");
    }

    @Override
    public void deactivate(String category) {
        System.out.println("## ServantLocator1 #" + id + " .deactivate() ##");
    }
}
