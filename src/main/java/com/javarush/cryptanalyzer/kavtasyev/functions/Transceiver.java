package com.javarush.cryptanalyzer.kavtasyev.functions;

import java.io.*;
import java.util.ArrayList;

public class Transceiver
{
	public ArrayList<String> readFile(String file) throws IOException
	{
		ArrayList<String> fileList = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		while (bufferedReader.ready())
		{
			fileList.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		return fileList;
	}

	public void writeFile(ArrayList<String> fileList, String path) throws IOException
	{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
		for(String line : fileList)
		{
			bufferedWriter.write(line);
		}
		bufferedWriter.close();
	}
}
