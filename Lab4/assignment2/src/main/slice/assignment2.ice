
#ifndef SR_DEMO_ICE
#define SR_DEMO_ICE

module assignment2 {

    enum Currency {EUR, USD, CHF};

    struct Loan {
        string interest;
        string paid;
        string toPay;
        int monthsLeft;
    };

    struct Investment {
            string interest;
            string moneyProvided;
            string moneyEarned;
            int monthsLeft;
            };

    sequence<Loan> loans;
    sequence<Investment> investments;

    interface Customer {
        void login();
        void logout();
        string calcInvestmentInterest(int periodInMonths, int amount, Currency currency);
        string calcLoadInterest(int periodInMoths, int amount, Currency currency);
        loans getLoans();
        investments getInvestments();
    };

};

#endif
