package impl;

import Ice.Current;
import assignment2.Currency;
import assignment2.Investment;
import assignment2.Loan;
import assignment2._CustomerDisp;
import server.FinancialDataHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by wiktor on 24/04/16.
 */
public class Customer extends _CustomerDisp {

    private boolean loggedIn = false;
    private LocalDateTime joinDate;
    private int delayedRepayments;

    public Customer() {
    }

    public Customer(LocalDateTime joinDate, int delayedRepayments) {
        this.joinDate = joinDate;
        this.delayedRepayments = delayedRepayments;
    }

    @Override
    public void login(Current __current) {
        loggedIn = true;
    }

    @Override
    public void logout(Current __current) {
        loggedIn = false;
    }

    @Override
    public String calcInvestmentInterest(int periodInMonths, int amount, Currency currency, Current __current) {
        return String.valueOf(((periodInMonths/12 + amount/100000)/2)
                + FinancialDataHolder.interests.get(FinancialDataHolder.financialCustomCurrency.get(currency)).getInterest()
                + FinancialDataHolder.interests.get(FinancialDataHolder.financialCustomCurrency.get(currency)).getExchange()/4
                + (joinDate.until(LocalDateTime.now(), ChronoUnit.YEARS) / 2.) + 3);
    }

    @Override
    public String calcLoadInterest(int periodInMoths, int amount, Currency currency, Current __current) {
        return String.valueOf(periodInMoths/12 - amount/100000.
                + FinancialDataHolder.interests.get(FinancialDataHolder.financialCustomCurrency.get(currency)).getInterest()
                + FinancialDataHolder.interests.get(FinancialDataHolder.financialCustomCurrency.get(currency)).getExchange()/4
                + delayedRepayments/4. + 3);
    }


    @Override
    public Loan[] getLoans(Current __current) {
        return new Loan[0];
    }

    @Override
    public Investment[] getInvestments(Current __current) {
        return new Investment[0];
    }


    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public int getDelayedRepayments() {
        return delayedRepayments;
    }

    public void setDelayedRepayments(int delayedRepayments) {
        this.delayedRepayments = delayedRepayments;
    }
}
