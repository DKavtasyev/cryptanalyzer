package com.javarush.cryptanalyzer.kavtasyev.repository;

import com.javarush.cryptanalyzer.kavtasyev.functions.*;

public enum FunctionCode
{
	ENCRYPT(new Encrypt()),
	DECRYPT(new Decrypt()),
	BRUTEFORCE_DECRYPT(new BruteForceDecrypt()),
	STATISTICAL_DECRYPT(new StatisticalDecrypt()),
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
