// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SessionTimeoutException.java

package exceptions;


public class SessionTimeoutException extends Exception
{

    public SessionTimeoutException()
    {
        super("Your session has been expired.");
    }

    public SessionTimeoutException(String message)
    {
        super(message);
    }

    public SessionTimeoutException(Throwable cause)
    {
        super(cause);
    }

    public SessionTimeoutException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
