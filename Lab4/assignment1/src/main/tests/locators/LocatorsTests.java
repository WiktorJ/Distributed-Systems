package locators;

import assignment1.CounterPrx;
import assignment1.CounterPrxHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wiktor on 24/04/16.
 */
public class LocatorsTests {

    @Test
    public void basicTests() {
        Ice.Communicator communicator = null;

        communicator = Ice.Util.initialize();
        Ice.ObjectPrx base0 = communicator.stringToProxy("c1/o1:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base1 = communicator.stringToProxy("c1/o2:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base2 = communicator.stringToProxy("c2/o1:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base3 = communicator.stringToProxy("c2/o2:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base4 = communicator.stringToProxy("c3/o1:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base5 = communicator.stringToProxy("c3/o2:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base6 = communicator.stringToProxy("c4/o1:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base7 = communicator.stringToProxy("c4/o2:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base8 = communicator.stringToProxy("c5/o11:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base9 = communicator.stringToProxy("c5/o22:tcp -h localhost -p 10000:udp -h localhost -p 10000");
        Ice.ObjectPrx base10 = communicator.stringToProxy("c5/o33:tcp -h localhost -p 10000:udp -h localhost -p 10000");

        CounterPrx counter0 = CounterPrxHelper.checkedCast(base0);
        CounterPrx counter1 = CounterPrxHelper.checkedCast(base0);
        CounterPrx counter12 = CounterPrxHelper.checkedCast(base1);
        CounterPrx counter2 = CounterPrxHelper.checkedCast(base2);
        CounterPrx counter3 = CounterPrxHelper.checkedCast(base3);
        CounterPrx counter4 = CounterPrxHelper.checkedCast(base4);
        CounterPrx counter5 = CounterPrxHelper.checkedCast(base5);
        CounterPrx counter6 = CounterPrxHelper.checkedCast(base6);
        CounterPrx counter7 = CounterPrxHelper.checkedCast(base7);
        CounterPrx counter8 = CounterPrxHelper.checkedCast(base8);
        CounterPrx counter9 = CounterPrxHelper.checkedCast(base9);
        CounterPrx counter10 = CounterPrxHelper.checkedCast(base10);
        CounterPrx counter11 = CounterPrxHelper.checkedCast(base10);

        if (counter0 == null) throw new Error("Invalid proxy");

        for (int i = 0; i < 10; i++) {
            counter0.increment();
        }
        Assert.assertEquals(11, counter1.increment());
        Assert.assertEquals(12, counter0.decrement());
        Assert.assertEquals(0, counter12.decrement());

        Assert.assertEquals(0, counter3.increment());
        Assert.assertEquals(0, counter2.decrement());
        Assert.assertEquals(0, counter3.decrement());
        Assert.assertEquals(0, counter2.increment());

        //for n = 2
        Assert.assertEquals(0, counter4.increment());
        Assert.assertEquals(0, counter4.increment());
        Assert.assertEquals(1, counter4.increment());
        Assert.assertEquals(1, counter5.increment());
        Assert.assertEquals(2, counter4.increment());
        Assert.assertEquals(2, counter5.increment());
        Assert.assertEquals(3, counter5.increment());

        Assert.assertEquals(0, counter6.increment());
        Assert.assertEquals(1, counter7.increment());
        Assert.assertEquals(2, counter6.increment());
        Assert.assertEquals(3, counter7.increment());
        Assert.assertEquals(4, counter6.decrement());
        Assert.assertEquals(3, counter7.increment());


        Assert.assertEquals(0, counter8.increment());
        Assert.assertEquals(1, counter8.increment());
        Assert.assertEquals(0, counter9.increment());
        Assert.assertEquals(1, counter9.increment());
        Assert.assertEquals(0, counter10.increment());
        Assert.assertEquals(1, counter10.increment());
        Assert.assertEquals(2, counter11.decrement());
        Assert.assertEquals(1, counter10.increment());

    }


}
