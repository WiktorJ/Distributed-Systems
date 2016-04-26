// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.2
//
// <auto-generated>
//
// Generated from file `assignment2.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package assignment2;

public interface _CustomerOperations
{
    void login(Ice.Current __current);

    void logout(Ice.Current __current);

    String calcInvestmentInterest(int periodInMonths, int amount, Currency currency, Ice.Current __current)
        throws UserNotLoggedException;

    String calcLoadInterest(int periodInMoths, int amount, Currency currency, Ice.Current __current)
        throws UserNotLoggedException;

    Loan[] getLoans(Ice.Current __current)
        throws UserNotLoggedException;

    Investment[] getInvestments(Ice.Current __current)
        throws UserNotLoggedException;
}
