package com.javarush.cryptanalyzer.kavtasyev.repository;

import com.javarush.cryptanalyzer.kavtasyev.functions.*;

public enum FunctionCode
{
	ENCRYPT(new Encrypt()),
	BRUTEFORCEDECRYPT(new BruteForceDecrypt()),
	STATISTICALDECRYPT(new StatisticalDecrypt()),
	PRINT_ALPHABET(new PrintAlphabet());

	private final Function function;

	FunctionCode(Function function)
	{
		this.function = function;
	}

	public Function getFunction()
	{
		return function;
	}


}
