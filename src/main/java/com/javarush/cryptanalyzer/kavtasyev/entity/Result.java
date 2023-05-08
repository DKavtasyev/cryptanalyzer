package com.javarush.cryptanalyzer.kavtasyev.entity;

import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

public class Result
{
	private ResultCode resultCode;

	private Exception exception;
	private String time;

	public Result(ResultCode resultCode, String time)
	{
		this.resultCode = resultCode;
		this.time = time;
	}

	public Result(ResultCode resultCode, Exception exception)
	{
		this.resultCode = resultCode;
		this.exception = exception;
	}

	public Result(ResultCode resultCode)
	{
		this.resultCode = resultCode;
	}


	public ResultCode getResultCode()
	{
		return resultCode;
	}

	public Exception getException()
	{
		return exception;
	}

	public String getTime()
	{
		return time;
	}
}
