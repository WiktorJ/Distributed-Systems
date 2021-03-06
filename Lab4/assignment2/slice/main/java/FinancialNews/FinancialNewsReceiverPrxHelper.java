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

/**
 * Provides type-specific helper functions.
 **/
public final class FinancialNewsReceiverPrxHelper extends Ice.ObjectPrxHelperBase implements FinancialNewsReceiverPrx
{
    private static final String __exchangeRate_name = "exchangeRate";

    public void exchangeRate(float rate, Currency curr1, Currency curr2)
    {
        exchangeRate(rate, curr1, curr2, null, false);
    }

    public void exchangeRate(float rate, Currency curr1, Currency curr2, java.util.Map<String, String> __ctx)
    {
        exchangeRate(rate, curr1, curr2, __ctx, true);
    }

    private void exchangeRate(float rate, Currency curr1, Currency curr2, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        end_exchangeRate(begin_exchangeRate(rate, curr1, curr2, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2)
    {
        return begin_exchangeRate(rate, curr1, curr2, null, false, false, null);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2, java.util.Map<String, String> __ctx)
    {
        return begin_exchangeRate(rate, curr1, curr2, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2, Ice.Callback __cb)
    {
        return begin_exchangeRate(rate, curr1, curr2, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_exchangeRate(rate, curr1, curr2, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2, Callback_FinancialNewsReceiver_exchangeRate __cb)
    {
        return begin_exchangeRate(rate, curr1, curr2, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, Currency curr1, Currency curr2, java.util.Map<String, String> __ctx, Callback_FinancialNewsReceiver_exchangeRate __cb)
    {
        return begin_exchangeRate(rate, curr1, curr2, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, 
                                              Currency curr1, 
                                              Currency curr2, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_exchangeRate(rate, curr1, curr2, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, 
                                              Currency curr1, 
                                              Currency curr2, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_exchangeRate(rate, curr1, curr2, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, 
                                              Currency curr1, 
                                              Currency curr2, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_exchangeRate(rate, curr1, curr2, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_exchangeRate(float rate, 
                                              Currency curr1, 
                                              Currency curr2, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_exchangeRate(rate, curr1, curr2, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_exchangeRate(float rate, 
                                               Currency curr1, 
                                               Currency curr2, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.Functional_VoidCallback __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_exchangeRate(rate, 
                                  curr1, 
                                  curr2, 
                                  __ctx, 
                                  __explicitCtx, 
                                  __synchronous, 
                                  new IceInternal.Functional_OnewayCallback(__responseCb, __exceptionCb, __sentCb));
    }

    private Ice.AsyncResult begin_exchangeRate(float rate, 
                                               Currency curr1, 
                                               Currency curr2, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__exchangeRate_name, __cb);
        try
        {
            __result.prepare(__exchangeRate_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeFloat(rate);
            Currency.__write(__os, curr1);
            Currency.__write(__os, curr2);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_exchangeRate(Ice.AsyncResult __iresult)
    {
        __end(__iresult, __exchangeRate_name);
    }

    private static final String __interestRate_name = "interestRate";

    public void interestRate(float rate, Currency curr)
    {
        interestRate(rate, curr, null, false);
    }

    public void interestRate(float rate, Currency curr, java.util.Map<String, String> __ctx)
    {
        interestRate(rate, curr, __ctx, true);
    }

    private void interestRate(float rate, Currency curr, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        end_interestRate(begin_interestRate(rate, curr, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr)
    {
        return begin_interestRate(rate, curr, null, false, false, null);
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr, java.util.Map<String, String> __ctx)
    {
        return begin_interestRate(rate, curr, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr, Ice.Callback __cb)
    {
        return begin_interestRate(rate, curr, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_interestRate(rate, curr, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr, Callback_FinancialNewsReceiver_interestRate __cb)
    {
        return begin_interestRate(rate, curr, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_interestRate(float rate, Currency curr, java.util.Map<String, String> __ctx, Callback_FinancialNewsReceiver_interestRate __cb)
    {
        return begin_interestRate(rate, curr, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_interestRate(float rate, 
                                              Currency curr, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_interestRate(rate, curr, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_interestRate(float rate, 
                                              Currency curr, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_interestRate(rate, curr, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_interestRate(float rate, 
                                              Currency curr, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_interestRate(rate, curr, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_interestRate(float rate, 
                                              Currency curr, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_VoidCallback __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_interestRate(rate, curr, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_interestRate(float rate, 
                                               Currency curr, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.Functional_VoidCallback __responseCb, 
                                               IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                               IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_interestRate(rate, 
                                  curr, 
                                  __ctx, 
                                  __explicitCtx, 
                                  __synchronous, 
                                  new IceInternal.Functional_OnewayCallback(__responseCb, __exceptionCb, __sentCb));
    }

    private Ice.AsyncResult begin_interestRate(float rate, 
                                               Currency curr, 
                                               java.util.Map<String, String> __ctx, 
                                               boolean __explicitCtx, 
                                               boolean __synchronous, 
                                               IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__interestRate_name, __cb);
        try
        {
            __result.prepare(__interestRate_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeFloat(rate);
            Currency.__write(__os, curr);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public void end_interestRate(Ice.AsyncResult __iresult)
    {
        __end(__iresult, __interestRate_name);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static FinancialNewsReceiverPrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static FinancialNewsReceiverPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static FinancialNewsReceiverPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static FinancialNewsReceiverPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static FinancialNewsReceiverPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static FinancialNewsReceiverPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, FinancialNewsReceiverPrx.class, FinancialNewsReceiverPrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::FinancialNews::FinancialNewsReceiver",
        "::Ice::Object"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[0];
    }

    public static void __write(IceInternal.BasicStream __os, FinancialNewsReceiverPrx v)
    {
        __os.writeProxy(v);
    }

    public static FinancialNewsReceiverPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            FinancialNewsReceiverPrxHelper result = new FinancialNewsReceiverPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
