package impl;

import Ice.Current;
import assignment1._CounterDisp;

/**
 * Created by wiktor on 24/04/16.
 */
public class Counter extends _CounterDisp {

    private long state = 0;

    @Override
    public long increment(Current __current) {
        return state++;
    }

    @Override
    public long decrement(Current __current) {
        return state--;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }
}
