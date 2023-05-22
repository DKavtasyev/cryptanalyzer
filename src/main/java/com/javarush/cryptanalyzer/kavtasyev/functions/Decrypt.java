package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.constants.FilePaths;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.exception.DecryptionException;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class Decrypt extends Function
{
	@Override
	public Result execute(CustomData customData)
	{
		String time;
		try
		{
			String encryptedFilePath = customData.getStr1();
			int key = Integer.parseInt(customData.getStr2());
			Instant start = Instant.now();

			String decryptedFilePath;
			if (encryptedFilePath.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_DECRYPT_WITH_KEY))
			{
				decryptedFilePath = FilePaths.OUTPUT_DEFAULT_FILE_FOR_DECRYPT_WITH_KEY;
			}
			else
			{
				int length = encryptedFilePath.length();                                       			 // Длина строки, содержащей путь к файлу, сохраняется в переменную.
				decryptedFilePath = encryptedFilePath.substring(0, length - 4) + "_decrypted_with_key"
						+ encryptedFilePath.substring(length - 4, length);   							// Создаётся новое имя файла, куда будет записан расшифрованный текст.

			}

			ArrayList<String> encryptedFile = transceiver.readFile(encryptedFilePath);                  // Создаётся лист для файла, предназначенного к расшифровке. Данный файл содержит зашифрованный текст.
			ArrayList<String> decryptedFile;                                             	    		// Создаётся лист для расшифрованного файла. В него будет записан текст после расшифровки.

			decryptedFile = service.shiftText(encryptedFile, key);
			transceiver.writeFile(decryptedFile, decryptedFilePath);
			System.out.printf("Текст расшифрован с ключом %d и записан в файл %s.%n", key, decryptedFilePath);

			Instant stop = Instant.now();
			time = getTime(start, stop);
		}
		catch (IOException e)
		{
			return new Result(ResultCode.ERROR, e);
		}
		catch (Exception e)
		{
			return new Result(ResultCode.ERROR, new DecryptionException("Дешифрование текста закончилось ошибкой!", e));
		}
		return new Result(ResultCode.OK, time);

	}
}
