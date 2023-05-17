package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.constants.FilePaths;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.exception.DecryptionException;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class BruteForceDecrypt extends Function
{
	public Result execute(CustomData customData)
	{
		String time;
		try
		{
			String encryptedFilePath = customData.getStr1();
			Instant start = Instant.now();

			String decryptedFilePath;                                                      		// Создаётся строка для нового имени файла
			if (encryptedFilePath.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_BRUTEFORCE_DECRYPT))	// Если выбран файл по умолчанию, то выходной файл так же имеет стандартное имя
			{
				decryptedFilePath = FilePaths.OUTPUT_DEFAULT_FILE_FOR_BRUTEFORCE_DECRYPT;
			}
			else 																				// если файл указан пользователем, то имя выходного файла будет формироваться по-другому.
			{
				int length = encryptedFilePath.length();                                        // Длина строки, содержащей путь к файлу, сохраняется в переменную.
				decryptedFilePath = encryptedFilePath.substring(0, length - 4) + "_decrypted"
						+ encryptedFilePath.substring(length - 4, length);   					// Создаётся новое имя файла, куда будет записан расшифрованный текст.
			}

			HashMap<Integer, Integer> correlation = new HashMap<>();                    	    // key - шифровальный ключ; value - корреляционный коэффициент. Создаётся ХэшМап, куда будет производиться запись значений корреляции текста с признаками текста вместе с соответствующим значением ключа по шифру Цезаря
			int correlationCoefficient;                                                 	    // Создаётся переменная, с помощью которой будет высчитываться корреляционный коэффициент для каждого значения ключа.
			ArrayList<String> fileToDecrypt = transceiver.readFile(encryptedFilePath);                  // Создаётся лист для файла, предназначенного к расшифровке. Данный файл содержит зашифрованный текст.
			ArrayList<String> decryptedFile;                                             	    // Создаётся лист для расшифрованного файла. В него будет записан текст после расшифровки.

			for (int i = 0; i < EncryptionAlphabet.getAlphabetLength(); i++)            	    // i - текущее значение ключа. Будет производиться подбор ключа методом перебора всех ключей и тестирования текста, полученного с использованием данного ключа, на наличие признаков речи.
			{
				StringBuilder file = new StringBuilder();
				decryptedFile = service.shiftText(fileToDecrypt, i);
				for(String s : decryptedFile)
				{
					file.append(s);
				}
				correlationCoefficient = service.recognizeTheText(file.toString());
				correlation.put(i, correlationCoefficient);
			}
			int key = 0;
			correlationCoefficient = 0;
			for(int i: correlation.keySet())
			{
				if (correlation.get(i) > correlationCoefficient)
				{
					correlationCoefficient = correlation.get(i);
					key = i;
				}
			}

			decryptedFile = service.shiftText(fileToDecrypt, key);
			Instant stop = Instant.now();
			transceiver.writeFile(decryptedFile, decryptedFilePath);
			System.out.printf("Текст расшифрован с ключом %d и записан в файл %s.%n", key, decryptedFilePath);
			time = getTime(start, stop);
		}
		catch (IOException e)
		{
			return new Result(ResultCode.ERROR, e);
		}
		catch (Exception e)
		{
			return new Result(ResultCode.ERROR, new DecryptionException("Дешифрование текста закончилось ошибкой.", e));
		}
		return new Result(ResultCode.OK, time);
	}
}
