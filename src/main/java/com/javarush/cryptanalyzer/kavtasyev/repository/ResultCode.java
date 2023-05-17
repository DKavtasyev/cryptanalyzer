package com.javarush.cryptanalyzer.kavtasyev.repository;

public enum ResultCode
{
	OK("Программа отработала успешно.\n"),
	ERROR("Ошибка!\n");

	private final String message;

	ResultCode(String message)
	{
		this.message = message;
	}

	public String getResultMessage()
	{
		return message;
	}
}
