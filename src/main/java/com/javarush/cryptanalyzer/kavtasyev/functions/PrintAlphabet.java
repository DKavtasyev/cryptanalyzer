package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.constants.FilePaths;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PrintAlphabet extends Function
{
	public Result execute(CustomData customData)
	{
		int count = 0;
		char[] alphabet = EncryptionAlphabet.getAlphabet();

		String path = FilePaths.OUTPUT_SERVICE_INFORMATION;

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path)))
		{

			for (char c: alphabet)
			{
				int n = c;
				bufferedWriter.write( (String.format("[%c]\tdecimal:%6d;\t\thex:%5x\t%d\n", c, n, n, count++)) );
			}
		}
		catch (IOException e)
		{
			return new Result (ResultCode.ERROR, e);
		}
		return new Result(ResultCode.OK);
	}
}