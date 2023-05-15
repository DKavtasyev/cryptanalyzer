package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.LetterOccurrence;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.exception.DecryptionException;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StatisticalDecrypt extends Function
{
	LetterOccurrence[] statisticsInNormalText;
	LetterOccurrence[] statisticsInEncryptedText;

	public Result execute(CustomData customData)
	{
		String time;
		try
		{
			Instant start = Instant.now();																		// Начало отсчёта времени на выполнение статистического анализа
			String encryptedFilePath = customData.getStr1();													// Извлечение пользовательских данных: путь к зашифрованному файлу
			String normalFilePath = customData.getStr2();														// Извлечение пользовательских данных: путь к опорному файлу
			int length = encryptedFilePath.length();                                        					// Длина строки, содержащей путь к файлу, сохраняется в переменную.
			String decryptedFilePath;                                                       					// Создаётся строка, содержащая путь и имя файла, сформированного в результате расшифровывания программой переданного ей файла
			decryptedFilePath = encryptedFilePath.substring(0, length - 4) +
				"_decryptedstatistically" + encryptedFilePath.substring(length - 4, length); 					// Создаётся новое имя файла, куда будет записан расшифрованный текст.
			ArrayList<String> encryptedFile = transceiver.readFile(encryptedFilePath);		 					// Чтение зашифрованного файла и сохранение его в лист
			ArrayList<String> normalFile = transceiver.readFile(normalFilePath);			 					// Чтение файла, с помощью которого будет сформирована статистика вхождений разных букв
			ArrayList<String> lowercaseEncryptedFile = (ArrayList<String>) encryptedFile.stream().
					map(s -> s.toLowerCase()).collect(Collectors.toList());
			ArrayList<String> lowercaseNormalFile = (ArrayList<String>) normalFile.stream().
					map(s -> s.toLowerCase()).collect(Collectors.toList());

			statisticsInNormalText = service.calculateStatistics(lowercaseNormalFile);							// Подсчёт частоты вхождений для каждой буквы в зашифрованном файле
			statisticsInEncryptedText = service.calculateStatistics(lowercaseEncryptedFile);					// Подсчёт частоты вхождений для каждой буквы в опорном файле
			ArrayList<String> decryptedFile;							 										// Создание листа для расшифрованного файла

			decryptedFile = replaceLetters(lowercaseEncryptedFile);												// Производится замена зашифрованной буквы на эквивалентную ей по частоте входимости в текст незашифрованную

			System.out.println("отсортированный массив по частоте вхождений: \n");
			for (int i = 0; i < statisticsInNormalText.length; i++)
			{
				System.out.println(String.format("Normal: [%c]: %f Cyphered: [%c]: %f ", statisticsInNormalText[i].getLetter(), statisticsInNormalText[i].getOccurrence(), statisticsInEncryptedText[i].getLetter(), statisticsInEncryptedText[i].getOccurrence()));
			}

			Instant stop = Instant.now();
			transceiver.writeFile(decryptedFile, decryptedFilePath);
			time = getTime(start, stop);
		}
		catch(IOException e)
		{
			return new Result(ResultCode.ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			return new Result(ResultCode.ERROR, new DecryptionException("Дешифрование текста закончилось ошибкой.", e));
		}

		return new Result(ResultCode.OK, time);
	}

	public ArrayList<String> replaceLetters(ArrayList<String> text)
	{
		ArrayList<String> revisedText = new ArrayList<>();
		for(String s : text)																		// Для каждой строки из зашифрованного текста происходит замена каждого её символа на эквивалентный по частоте встречаемости правильный символ
		{
			char[] lineLetters = s.toCharArray();													// Строка зашифрованного текста преобразуется в массив символов
			StringBuilder builder = new StringBuilder();											// Создаётся накопитель для заменённых символов на правильные
			for (int i = 0; i < lineLetters.length; i++)											// i - номер символа в зашифрованной строке. Для каждого символа из строки зашифрованного текста
			{
				for (int j = 0; j < statisticsInEncryptedText.length; j++)							// j - номер пары символ-частота вхождений в массиве статистики для зашифрованного текста. Для каждого символа из массива статистики вхождений зашифрованных символов
				{
					if(lineLetters[i] == statisticsInEncryptedText[j].getLetter())					// Если буква из массива статистики вхождений букв в зашифрованный текст совпадает с текущей буквой из строки зашифрованного текста,
					{
						lineLetters[i] = statisticsInNormalText[j].getLetter();						// то её нужно заменить на правильную букву, эквивалентную по частоте встречаемости в тексте
						break;																		// После этого перебор прекращается, программа переходит к следующей букве из строки.
					}
				}
				builder.append(lineLetters[i]);
			}
			revisedText.add(builder + "\n");
		}
		return revisedText;
	}
}


// ---------------------------------------------------------------------------------------------------------------------
/*
Таблица сопоставления букв

System.out.println("отсортированный массив по частоте вхождений: \n");
for (int i = 0; i < statisticsInNormalText.length; i++)
{
	System.out.println(String.format("Normal: [%c]: %f Cyphered: [%c]: %f ", statisticsInNormalText[i].getLetter(), statisticsInNormalText[i].getOccurrence(), statisticsInEncryptedText[i].getLetter(), statisticsInEncryptedText[i].getOccurrence()));
}
 */
// ---------------------------------------------------------------------------------------------------------------------
/*
Поиск и замена знаков пунктуации посередине слова с соседними по частоте повторяемости буквами

			while (true)
			{
				StringBuilder currentText = new StringBuilder();
				for(String s : decryptedFile)
				{
					currentText.append(s + "\n");
				}
				Pattern punctuation = Pattern.compile("(\\p{P})([а-яА-ЯЁё]+)");
				Matcher m = punctuation.matcher(currentText);
				if(m.find())
				{
					int presentCorrelation = service.recognizeTheText(currentText.toString());
					int previousLetterCorrelation;
					int nextLetterCorrelation;
					StringBuilder previousLetterText = new StringBuilder();
					StringBuilder nextLetterText = new StringBuilder();
					char punct = m.group(1).charAt(0);
					for (int i = 0; i < statisticsInNormalText.length; i++)
					{
						if (punct == statisticsInNormalText[i].getLetter())
						{
							if (i > 0)
							{
								char temp = '$';
								BufferedReader reader = new BufferedReader(new StringReader(currentText.toString()));
								while (reader.ready())
								{
									previousLetterText.append(reader.readLine().replace(punct, temp).replace(statisticsInNormalText[i - 1].getLetter(), punct).replace(temp, statisticsInNormalText[i - 1].getLetter()) );
								}
								previousLetterCorrelation = service.recognizeTheText(previousLetterText.toString());
							}
							if (i < statisticsInNormalText.length)
							{

							}



						}
					}

				}
				else
					break;
			}*/

// ---------------------------------------------------------------------------------------------------------------------

/*
			Часть кода, которая заменяет соседние буквы, и считает корреляцию с речью

			StringBuilder previousFile = new StringBuilder();
			for(String s : decryptedFile)
			{
				previousFile.append(s);
			}
			int previousCorrelation = service.recognizeTheText(previousFile.toString());

			for (int i = 0; i < statisticsInNormalText.length - 2; i++)
			{
				if (statisticsInNormalText[i].getOccurrence() < 0.00001)
					continue;

				ArrayList<String> revisedFile = service.swapLetters(decryptedFile, statisticsInNormalText[i].getLetter(), statisticsInNormalText[i + 1].getLetter());

				StringBuilder newFile = new StringBuilder();
				for(String s : decryptedFile)
				{
					newFile.append(s);
				}
				int nextLetterCorrelation = service.recognizeTheText(newFile.toString());
				if (nextLetterCorrelation > previousCorrelation)
				{
					decryptedFile = revisedFile;
					previousCorrelation = nextLetterCorrelation;
				}

			}*/