package com.javarush.cryptanalyzer.kavtasyev.entity;

public class CustomData
{
	private String flag;
	private String str1;
	private String str2;

	public CustomData(String flag)
	{
		this.flag = flag;
	}

	public CustomData(String flag, String str1)
	{
		this.flag = flag;
		this.str1 = str1;
	}

	public CustomData(String flag, String str1, String str2)
	{
		this.flag = flag;
		this.str1 = str1;
		this.str2 = str2;
	}

	public String getFlag()
	{
		return flag;
	}

	public String getStr1()
	{
		return str1;
	}

	public String getStr2()
	{
		return str2;
	}
}
