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
// Generated from file `assignment1.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package assignment1;

public abstract class Callback_Counter_decrement
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackLong
{
    public final void __completed(Ice.AsyncResult __result)
    {
        CounterPrxHelper.__decrement_completed(this, __result);
    }
}
