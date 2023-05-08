package com.javarush.cryptanalyzer.kavtasyev.entity;

public class LetterOccurrence
{
	private char letter;
	private double occurrence;

	public LetterOccurrence(char letter, double occurrence)
	{
		this.letter = letter;
		this.occurrence = occurrence;
	}

	public char getLetter()
	{
		return letter;
	}

	public double getOccurrence()
	{
		return occurrence;
	}
}
