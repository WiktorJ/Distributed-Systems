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
// Generated from file `FinancialNews.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package FinancialNews;

public final class FinancialNewsServerHolder extends Ice.ObjectHolderBase<FinancialNewsServer>
{
    public
    FinancialNewsServerHolder()
    {
    }

    public
    FinancialNewsServerHolder(FinancialNewsServer value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof FinancialNewsServer)
        {
            value = (FinancialNewsServer)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _FinancialNewsServerDisp.ice_staticId();
    }
}