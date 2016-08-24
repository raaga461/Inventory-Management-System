// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NoDataException.java

package exceptions;


public class NoDataException extends Exception
{

    public NoDataException()
    {
    }

    public NoDataException(String message)
    {
        super(message);
    }

    public NoDataException(Throwable cause)
    {
        super(cause);
    }

    public NoDataException(String message, Throwable cause)
    {
        super(message, cause);
    }
    private static final long serialVersionUID = 1L;
}
