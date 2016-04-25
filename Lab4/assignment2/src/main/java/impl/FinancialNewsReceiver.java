package impl;

import FinancialNews.Currency;
import FinancialNews._FinancialNewsReceiverDisp;
import Ice.Current;
import server.FinancialDataHolder;

import java.util.Optional;

/**
 * Created by wiktor on 24/04/16.
 */
public class FinancialNewsReceiver extends _FinancialNewsReceiverDisp {


    @Override
    public void interestRate(float rate, Currency curr, Current __current) {
        FinancialDataHolder.interests.get(curr).setInterest(rate);
        System.out.println("Interest: " + rate + curr.toString());
    }

    @Override
    public void exchangeRate(float rate, Currency curr1, Currency curr2, Current __current) {
        FinancialDataHolder.interests.get(curr1).setExchange(rate);
        System.out.println("Exchange: " + rate + curr1.toString() + " " + curr2.toString());
    }


}
