package server;

import Evictor.EvictorBase;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import impl.Customer;

/**
 * Created by wiktor on 24/04/16.
 */
public class BankServantEvictor extends EvictorBase {

    public BankServantEvictor(int size) {
        super(size);
    }

    @Override
    public Object add(Current c, LocalObjectHolder cookie) {
        return new Customer();
    }

    @Override
    public void evict(Object servant, java.lang.Object cookie) {

    }
}
