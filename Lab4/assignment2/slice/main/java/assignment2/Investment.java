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

public class Investment implements java.lang.Cloneable, java.io.Serializable
{
    public String interest;

    public String moneyProvided;

    public String moneyEarned;

    public int monthsLeft;

    public Investment()
    {
        interest = "";
        moneyProvided = "";
        moneyEarned = "";
    }

    public Investment(String interest, String moneyProvided, String moneyEarned, int monthsLeft)
    {
        this.interest = interest;
        this.moneyProvided = moneyProvided;
        this.moneyEarned = moneyEarned;
        this.monthsLeft = monthsLeft;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Investment _r = null;
        if(rhs instanceof Investment)
        {
            _r = (Investment)rhs;
        }

        if(_r != null)
        {
            if(interest != _r.interest)
            {
                if(interest == null || _r.interest == null || !interest.equals(_r.interest))
                {
                    return false;
                }
            }
            if(moneyProvided != _r.moneyProvided)
            {
                if(moneyProvided == null || _r.moneyProvided == null || !moneyProvided.equals(_r.moneyProvided))
                {
                    return false;
                }
            }
            if(moneyEarned != _r.moneyEarned)
            {
                if(moneyEarned == null || _r.moneyEarned == null || !moneyEarned.equals(_r.moneyEarned))
                {
                    return false;
                }
            }
            if(monthsLeft != _r.monthsLeft)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::assignment2::Investment");
        __h = IceInternal.HashUtil.hashAdd(__h, interest);
        __h = IceInternal.HashUtil.hashAdd(__h, moneyProvided);
        __h = IceInternal.HashUtil.hashAdd(__h, moneyEarned);
        __h = IceInternal.HashUtil.hashAdd(__h, monthsLeft);
        return __h;
    }

    public Investment
    clone()
    {
        Investment c = null;
        try
        {
            c = (Investment)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(interest);
        __os.writeString(moneyProvided);
        __os.writeString(moneyEarned);
        __os.writeInt(monthsLeft);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        interest = __is.readString();
        moneyProvided = __is.readString();
        moneyEarned = __is.readString();
        monthsLeft = __is.readInt();
    }

    static public void
    __write(IceInternal.BasicStream __os, Investment __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public Investment
    __read(IceInternal.BasicStream __is, Investment __v)
    {
        if(__v == null)
        {
             __v = new Investment();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final Investment __nullMarshalValue = new Investment();

    public static final long serialVersionUID = 134013514349470107L;
}
