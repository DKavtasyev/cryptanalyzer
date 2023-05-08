package com.javarush.cryptanalyzer.kavtasyev.exception;

public class EncryptionException extends Exception
{
	public EncryptionException()
	{
	}

	public EncryptionException(String message)
	{
		super(message);
	}

	public EncryptionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public EncryptionException(Throwable cause)
	{
		super(cause);
	}
}
