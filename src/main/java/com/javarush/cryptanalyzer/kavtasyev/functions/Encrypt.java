package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.exception.EncryptionException;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class Encrypt extends Function
{
	public Result execute(CustomData customData)
	{
		String time;
		try
		{
			String unencryptedFilePath = customData.getStr1();
			int key = Integer.parseInt(customData.getStr2());
			Instant start = Instant.now();                                                  // Фиксируется начальный отсчёт времени шифрования для измерения затраченного времени на операцию
			int length = unencryptedFilePath.length();                                      // Длина строки, содержащей путь к файлу, сохраняется в переменную.
			String encryptedFilePath;                                                       // Создаётся строка для нового имени файла
			encryptedFilePath = unencryptedFilePath.substring(0, length - 4) +
					"_encrypted" + unencryptedFilePath.substring(length - 4, length);       // Создаётся новое имя файла, куда будет записан зашифрованный текст.
			ArrayList<String> unencryptedFile = transceiver.readFile(unencryptedFilePath);  // Создаётся лист для незашифрованного файла. Он будет содержать незашифрованный текст.
			ArrayList<String> encryptedFile = new ArrayList<>();                            // Создаётся лист для зашифрованного файла.
			char[] alphabet = EncryptionAlphabet.getAlphabet();                             // Делается рабочая копия алфовита шифрования.

			for (String s : unencryptedFile)                                                // Производится построчный перебор незашифрованного текста
			{
				char[] lineLetters = s.toCharArray();                                       // Строка преобразуется в массив символов char
				StringBuilder builder = new StringBuilder();

				for (int i = 0; i < lineLetters.length; i++)                                // Производится перебор букв в строке
				{
					for (int j = 0; j < alphabet.length; j++)                               // Производится перебор символов из алфавита и сравнение с буквой из строки
					{
						if (lineLetters[i] == alphabet[j])                                  // Если буква из алфовита совпадает с буквой из строки,
						{
							lineLetters[i] = alphabet[(j + key) % alphabet.length];   // То происходит замена буквы из строки на символ из алфавита, сдвинутый на keyNumber позиций вправо (в сторону увеличения)
							break;                                                          // После этого поиск символа в алфавите шифрования прекращается.
						}
					}
					builder.append(lineLetters[i]);                                         // Сдвинутая буква добавляется в зашифрованную строку. Если символ из строки не имеется в алфавите шифрования, то она добавляется без сдвига.
				}                                                                           // После происходит переход к следующему символу из строки.
				encryptedFile.add(builder + "\n");                                          // Когда все символы из строки перебраны, вся строка зашифрована. Она добавляется в лист зашифрованного текста.
			}                                                                               // Когда все строки перебраны, текст полностью зашифрован и готов к записи в файл.
			Instant stop = Instant.now();                                                   // Измерение времени, в течение которого происходило шифрование.
			transceiver.writeFile(encryptedFile, encryptedFilePath);                        // Производится запись зашифрованного текста в файл из листа encryptedFile.

			time = getTime(start, stop);                                                     // Вычисление времени проведения шифрования текста и вывод данных на экран.
		}
		catch (IOException e)
		{
			return new Result(ResultCode.ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			return new Result(ResultCode.ERROR, new EncryptionException("Шифрование текста закончилось ошибкой.", e));
		}
		return new Result(ResultCode.OK, time);
	}
}
