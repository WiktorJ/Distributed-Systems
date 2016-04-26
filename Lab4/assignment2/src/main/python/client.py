import Ice
import sys
import traceback

import assignment2 as ass


status = 0
ic = None
customer = None
try:
    ic = Ice.initialize(sys.argv)
    base = ic.propertyToProxy("Customer.Proxy")
    customer = ass.CustomerPrx.checkedCast(base)
    if not customer:
        raise RuntimeError("Invalid proxy")
    customer.login()
    inp = input("==> ")
    while inp != "exit":
        if inp == "loan":
            months = int(input("moths: "))
            amount = int(input("amount: "))
            currency = ass.Currency.valueOf(int(input("currency(0 - EUR, 1 - USD, 2 - CHF):")))
            customer.begin_calcLoadInterest(20, 1000, ass.Currency.EUR, lambda interest: print(interest), lambda ex: print("error"))
        if inp == "inv":
            months = int(input("moths: "))
            amount = int(input("amount: "))
            currency = ass.Currency.valueOf(int(input("currency(0 - EUR, 1 - USD, 2 - CHF):")))
            print(customer.calcInvestmentInterest(months, amount, currency))

        inp = input("==> ")

    customer.logout()

except:
    traceback.print_exc()
    status = 1
    if customer:
        customer.logout()

if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1


sys.exit(status)
