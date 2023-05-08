package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.LetterOccurrence;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.exception.DecryptionException;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class StatisticalDecrypt extends Function
{
	public Result execute(CustomData customData)
	{
		String time;
		try
		{
			Instant start = Instant.now();
			String encryptedFilePath = customData.getStr1();
			String normalFilePath = customData.getStr2();
			int length = encryptedFilePath.length();                                        // Длина строки, содержащей путь к файлу, сохраняется в переменную.
			String decryptedFilePath;                                                       // Создаётся строка для нового имени файла
			decryptedFilePath = encryptedFilePath.substring(0, length - 4) +
				"_decryptedstatistically" + encryptedFilePath.substring(length - 4, length); // Создаётся новое имя файла, куда будет записан расшифрованный текст.
			ArrayList<String> fileToDecrypt = transceiver.readFile(encryptedFilePath);
			ArrayList<String> normalFile = transceiver.readFile(normalFilePath);
			ArrayList<String> decryptedFile = new ArrayList<>();

			LetterOccurrence[] statisticsInNormalText = service.calculateStatistics(normalFile);
			LetterOccurrence[] statisticsInEncryptedText = service.calculateStatistics(fileToDecrypt);

			for(String s : fileToDecrypt)
			{
				char[] lineLetters = s.toCharArray();
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < lineLetters.length; i++)
				{
					for (int j = 0; j < statisticsInEncryptedText.length; j++)
					{
						if(lineLetters[i] == statisticsInEncryptedText[j].getLetter())
							builder.append(statisticsInNormalText[j].getLetter());
					}
				}
				decryptedFile.add(builder + "\n");
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

}
