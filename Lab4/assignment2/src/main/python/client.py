import Ice
import sys
import traceback

import assignment2 as ass


status = 0
ic = None
try:
    ic = Ice.initialize(sys.argv)
    base = ic.propertyToProxy("Customer.Proxy")
    printer = ass.CustomerPrx.checkedCast(base)
    if not printer:
        raise RuntimeError("Invalid proxy")

    print(printer.calcLoadInterest(20, 10000,  ass.Currency.EUR))
except:
    traceback.print_exc()
    status = 1

if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1

sys.exit(status)
