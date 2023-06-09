package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.constants.FrequentWords;
import com.javarush.cryptanalyzer.kavtasyev.entity.LetterOccurrence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Service
{
	public int recognizeTheText(String text)
	{
		int correlationCoefficient = 0;
		for(String prefix : FrequentWords.PREFIXES)
		{
			Pattern prefixes = Pattern.compile(String.format("( |^)%s[А-Яа-яЁё]+", prefix));
			Matcher m = prefixes.matcher(text);
			while(m.find())
				correlationCoefficient++;
		}

		for(String ending : FrequentWords.ENDINGS)
		{
			Pattern endings = Pattern.compile(String.format("[А-Яа-яЁё]+%s( |$|\\p{P})", ending));
			Matcher m = endings.matcher(text);
			while(m.find())
				correlationCoefficient++;
		}

		for(String frequentWord : FrequentWords.FREQUENT_WORDS)
		{
			Pattern frequentWords = Pattern.compile(String.format("( |^)%s( |$|\\p{P})", frequentWord));
			Matcher m = frequentWords.matcher(text);
			while(m.find())
				correlationCoefficient++;
		}

		Pattern punctuation = Pattern.compile("([а-яА-ЯЁё]+)(\\p{P})([ $\\n])");
		Matcher m = punctuation.matcher(text);
		while(m.find())
			correlationCoefficient++;

		return correlationCoefficient;
	}

	public LetterOccurrence[] calculateStatistics(ArrayList<String> text)
	{
		TreeMap<Character, Double> lettersStatistics = new TreeMap<>();
		char[] alphabet = EncryptionAlphabet.getLowercaseAlphabet();
		int numberOfLetters = 0;

		for(char c : alphabet)
		{
			lettersStatistics.put(c, 0.0);
		}

		for(String s : text)
		{
			char[] lineLetters = s.toCharArray();
			for (int i = 0; i < lineLetters.length; i++)
			{
				if (lettersStatistics.containsKey(lineLetters[i]))
				{
					lettersStatistics.put(lineLetters[i], lettersStatistics.get(lineLetters[i]) + 1);
					numberOfLetters++;
				}
			}
		}
		int n = 0;
		LetterOccurrence[] letterOccurrence = new LetterOccurrence[lettersStatistics.size()];
		for (char c : lettersStatistics.keySet())
		{
			letterOccurrence[n++] = new LetterOccurrence(c, lettersStatistics.get(c) / numberOfLetters);
		}

		for (int i = 0; i < letterOccurrence.length - 1; i++)
		{
			for (int j = 0; j < letterOccurrence.length - i - 1; j++)
			{
				if (letterOccurrence[j].getOccurrence() > letterOccurrence[j + 1].getOccurrence())
				{
					LetterOccurrence l = letterOccurrence[j];
					letterOccurrence[j] = letterOccurrence[j + 1];
					letterOccurrence[j + 1] = l;
				}
			}
		}
		return letterOccurrence;
	}

	public ArrayList<String> shiftText(ArrayList<String> fileToDecrypt, int key)
	{
		ArrayList<String> shiftedFile = new ArrayList<>();
		char[] alphabet = EncryptionAlphabet.getAlphabet();

		for (String s : fileToDecrypt)                                          // перебор строк из фойла с их расшифрованием с помощью текущего ключа
		{
			StringBuilder builder = new StringBuilder();
			char[] lineLetters = s.toCharArray();
			for (int j = 0; j < lineLetters.length; j++)                               // j - индекс буквы в строке. перебор букв в строке со сдвигом каждой буквы
			{
				for (int k = 0; k < alphabet.length; k++)                       // k - индекс буквы из строки в алфавите. определение номера буквы в шифровальном алфавите и сдвиг её на величину текущего ключа
				{
					if (lineLetters[j] == alphabet[k])
					{
						if (k < key)
							k += alphabet.length;
						lineLetters[j] = alphabet[k - key];
						break;
					}
				}
				builder.append(lineLetters[j]);
			}
			shiftedFile.add(builder + "\n");
		}
		return shiftedFile;
	}

	public ArrayList<String> swapLetters(ArrayList<String> list, char c1, char c2)
	{
		ArrayList<String> newList = new ArrayList<>();
		char temp = '$';
		for (String s : list)
		{
			newList.add(s.replace(c1, temp).replace(c2, c1).replace(temp, c2));
		}
		return newList;
	}
}
