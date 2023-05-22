package com.javarush.cryptanalyzer.kavtasyev;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

public class Temp
{
	public static void main(String[] args) throws IOException
	{
		try (FileOutputStream fis = new FileOutputStream("input.txt"))
		{
			fis.write(136);
		}
		catch (IOException e)
		{

		}
	}
}
