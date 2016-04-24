package server;

import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.UserException;
import impl.Counter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wiktor on 24/04/16.
 */
public class PThreeLocator extends AbstractLocator {

    private Counter[] servants;
    private int currentPosition;

    public PThreeLocator(String id, int n) {
        super(id);
        servants = new Counter[n];
        for (int i = 0; i < servants.length; i++) {
            servants[i] = new Counter();
        }
        currentPosition = 0;
        System.out.println("## PTwoLocator(" + id + ") ##");
    }


    @Override
    public Object locate(Current current, LocalObjectHolder localObjectHolder) throws UserException {
        return servants[getNext()];
    }

    private int getNext() {
        if (currentPosition == servants.length) {
            currentPosition = 0;
        }
        return currentPosition++;
    }

}
