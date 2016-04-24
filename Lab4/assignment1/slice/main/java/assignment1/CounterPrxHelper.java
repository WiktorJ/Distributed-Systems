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

/**
 * Provides type-specific helper functions.
 **/
public final class CounterPrxHelper extends Ice.ObjectPrxHelperBase implements CounterPrx
{
    private static final String __decrement_name = "decrement";

    public long decrement()
    {
        return decrement(null, false);
    }

    public long decrement(java.util.Map<String, String> __ctx)
    {
        return decrement(__ctx, true);
    }

    private long decrement(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__decrement_name);
        return end_decrement(begin_decrement(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_decrement()
    {
        return begin_decrement(null, false, false, null);
    }

    public Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx)
    {
        return begin_decrement(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_decrement(Ice.Callback __cb)
    {
        return begin_decrement(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_decrement(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_decrement(Callback_Counter_decrement __cb)
    {
        return begin_decrement(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, Callback_Counter_decrement __cb)
    {
        return begin_decrement(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_decrement(IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_decrement(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_decrement(IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_decrement(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_decrement(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_decrement(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, 
                                            boolean __explicitCtx, 
                                            boolean __synchronous, 
                                            IceInternal.Functional_LongCallback __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_decrement(__ctx, __explicitCtx, __synchronous, 
                               new IceInternal.Functional_TwowayCallbackLong(__responseCb, __exceptionCb, __sentCb)
                                   {
                                       public final void __completed(Ice.AsyncResult __result)
                                       {
                                           CounterPrxHelper.__decrement_completed(this, __result);
                                       }
                                   });
    }

    private Ice.AsyncResult begin_decrement(java.util.Map<String, String> __ctx, 
                                            boolean __explicitCtx, 
                                            boolean __synchronous, 
                                            IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__decrement_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__decrement_name, __cb);
        try
        {
            __result.prepare(__decrement_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public long end_decrement(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __decrement_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            long __ret;
            __ret = __is.readLong();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __decrement_completed(Ice.TwowayCallbackLong __cb, Ice.AsyncResult __result)
    {
        assignment1.CounterPrx __proxy = (assignment1.CounterPrx)__result.getProxy();
        long __ret = 0;
        try
        {
            __ret = __proxy.end_decrement(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    private static final String __increment_name = "increment";

    public long increment()
    {
        return increment(null, false);
    }

    public long increment(java.util.Map<String, String> __ctx)
    {
        return increment(__ctx, true);
    }

    private long increment(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__increment_name);
        return end_increment(begin_increment(__ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_increment()
    {
        return begin_increment(null, false, false, null);
    }

    public Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx)
    {
        return begin_increment(__ctx, true, false, null);
    }

    public Ice.AsyncResult begin_increment(Ice.Callback __cb)
    {
        return begin_increment(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_increment(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_increment(Callback_Counter_increment __cb)
    {
        return begin_increment(null, false, false, __cb);
    }

    public Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, Callback_Counter_increment __cb)
    {
        return begin_increment(__ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_increment(IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_increment(null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_increment(IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_increment(null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_increment(__ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_LongCallback __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_increment(__ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, 
                                            boolean __explicitCtx, 
                                            boolean __synchronous, 
                                            IceInternal.Functional_LongCallback __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_increment(__ctx, __explicitCtx, __synchronous, 
                               new IceInternal.Functional_TwowayCallbackLong(__responseCb, __exceptionCb, __sentCb)
                                   {
                                       public final void __completed(Ice.AsyncResult __result)
                                       {
                                           CounterPrxHelper.__increment_completed(this, __result);
                                       }
                                   });
    }

    private Ice.AsyncResult begin_increment(java.util.Map<String, String> __ctx, 
                                            boolean __explicitCtx, 
                                            boolean __synchronous, 
                                            IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__increment_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__increment_name, __cb);
        try
        {
            __result.prepare(__increment_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            __result.writeEmptyParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public long end_increment(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __increment_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            long __ret;
            __ret = __is.readLong();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __increment_completed(Ice.TwowayCallbackLong __cb, Ice.AsyncResult __result)
    {
        assignment1.CounterPrx __proxy = (assignment1.CounterPrx)__result.getProxy();
        long __ret = 0;
        try
        {
            __ret = __proxy.end_increment(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static CounterPrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), CounterPrx.class, CounterPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static CounterPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), CounterPrx.class, CounterPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static CounterPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), CounterPrx.class, CounterPrxHelper.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @param __ctx The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    public static CounterPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), CounterPrx.class, CounterPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @return A proxy for this type.
     **/
    public static CounterPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, CounterPrx.class, CounterPrxHelper.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param __obj The untyped proxy.
     * @param __facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    public static CounterPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, CounterPrx.class, CounterPrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::assignment1::Counter"
    };

    /**
     * Provides the Slice type ID of this type.
     * @return The Slice type ID.
     **/
    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, CounterPrx v)
    {
        __os.writeProxy(v);
    }

    public static CounterPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            CounterPrxHelper result = new CounterPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}