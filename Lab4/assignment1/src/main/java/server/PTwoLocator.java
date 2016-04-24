package server;

import Ice.*;
import Ice.Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import impl.Counter;

/**
 * Created by wiktor on 24/04/16.
 */
public class PTwoLocator extends AbstractLocator {

    public PTwoLocator(String id) {
        super(id);
        System.out.println("## PTwoLocator(" + id + ") ##");
    }


    @Override
    public Object locate(Current current, LocalObjectHolder localObjectHolder) throws UserException {
        return new Counter();
    }

}
