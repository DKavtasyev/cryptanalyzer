package com.javarush.cryptanalyzer.kavtasyev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

public class Temp
{
	public static void main(String[] args) throws IOException
	{
		ArrayList<String> list = new ArrayList<>();
		Collections.addAll(list, "мама мыла раму.", "рамено", "носорог", "мамонт");

		char c1 = 'м', c2 = 'р';

		ArrayList<String> newList = new ArrayList<>();
		char temp = '$';
		for (String s : list)
		{
			newList.add(s.replace(c1, temp).replace(c2, c1).replace(temp, c2));
		}
		for(String s1 : newList)
		{
			System.out.println(s1);
		}
	}
}
