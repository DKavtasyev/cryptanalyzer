package com.javarush.cryptanalyzer.kavtasyev;

import com.javarush.cryptanalyzer.kavtasyev.app.Application;
import com.javarush.cryptanalyzer.kavtasyev.controller.Controller;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.view.ConsoleView;
import com.javarush.cryptanalyzer.kavtasyev.view.View;

/*
-e C:\IdeaProjects\JavaRush_University\txt\CryptAnalyzer\1.txt 10
-d C:\IdeaProjects\JavaRush_University\txt\CryptAnalyzer\1_encrypted.txt
-s C:\IdeaProjects\JavaRush_University\txt\CryptAnalyzer\1_encrypted.txt C:\IdeaProjects\JavaRush_University\txt\CryptAnalyzer\normal.txt
*/

public class Main
{
	public static void main(String[] args)
	{
		View view = new ConsoleView();
		Controller Controller = new Controller(view);
		Application application = new Application(Controller);

		Result result = application.run();
		application.printResult(result);
	}
}


























/*
---------------------------------------------------------------------------------------------------------
    Чтение пути к файлу и ключа шифрования с консоли
---------------------------------------------------------------------------------------------------------

	public static void readParameters()
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
		{
			textFile = reader.readLine();
			key = Integer.parseInt(reader.readLine());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/



/*
----------------------------------------------------------------------------------------------------------
    часть предыдущего варианта функции шифрования
----------------------------------------------------------------------------------------------------------

try (BufferedReader bufferedReader = new BufferedReader(new FileReader(unencryptedFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encryptedFile)))
        {
            while (bufferedReader.ready())
            {
                String line = bufferedReader.readLine();
                StringBuilder builder = new StringBuilder();
                char[] charArray = line.toCharArray();
                for (char p : charArray)                              // А-Яа-яЁё. ,";:-!?
                {
                    if (p >= 32 && p <= 34)				p = (char) (p - 32);            // 0...2
                    else if (p >= 44 && p <= 46)		p = (char) (p - 41);            // 3...5
                    else if (p == 58 || p == 59)		p = (char) (p - 52);            // 6, 7
                    else if (p == 63)					p = (char) (p - 55);            // 8
                    else if (p == 1025)					p = (char) (p - 1016);          // 9
                    else if (p >= 1040 && p <= 1103)	p = (char) (p - 1030);			// 10...73
                    else if (p == 1105)					p = (char) (p - 1031);          // 74
                    else
                    {
                        builder.append(p);
                        continue;
                    }

                    char c = (char) ((p + keyNumber) % 75);

                    if (c >= 0 && c <= 2)				c = (char) (c + 32);
                    else if (c >= 3 && c <= 5)			c = (char) (c + 41);
                    else if (c == 6 || c == 7)			c = (char) (c + 52);
                    else if (c == 8)					c = (char) (c + 55);
                    else if (c == 9)					c = (char) (c + 1016);
                    else if (c >= 10 && c <= 73)		c = (char) (c + 1030);
                    else if (c == 74)					c = (char) (c + 1031);
                    else
                        throw new EncryptionException();			// Да, выброс исключения здесь немного не в тему. По сути, программа исключает выброс при любых событиях
                    builder.append(c);
                }

                bufferedWriter.write(builder.toString() + "\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
 */

/*
----------------------------------------------------------------------------------------------------------
    Часть предыдущего варианта функции дешифрования
-----------------------------------------------------------------------------------------------------------
for (int i = 0; i < 75; i++)
        {
            for (String line : fileToDecrypt)
            {
                StringBuilder builder = new StringBuilder();
                char[] charArray = line.toCharArray();
                for (char c : charArray)
                {
                    {
                        if (c >= 32 && c <= 34) c = (char) (c - 32);            // 0...2
                        else if (c >= 44 && c <= 46) c = (char) (c - 41);            // 3...5
                        else if (c == 58 || c == 59) c = (char) (c - 52);            // 6, 7
                        else if (c == 63) c = (char) (c - 55);            // 8
                        else if (c == 1025) c = (char) (c - 1016);          // 9
                        else if (c >= 1040 && c <= 1103) c = (char) (c - 1030);            // 10...73
                        else if (c == 1105) c = (char) (c - 1031);          // 74
                        else
                        {
                            builder.append(c);
                            continue;
                        }

                        if (c < i)
                            c += 75;
                        char p = (char) (c - i);

                        if (p >= 0 && p <= 2) p = (char) (c + 32);        // 32...34
                        else if (p >= 3 && p <= 5) p = (char) (c + 41);        // 44...46
                        else if (p == 6 || p == 7) p = (char) (c + 52);        // 58, 59
                        else if (p == 8) p = (char) (c + 55);        // 63
                        else if (p == 9) p = (char) (c + 1016);        // 1025
                        else if (p >= 10 && p <= 73) p = (char) (c + 1030);        // 1040...1103
                        else if (p == 74) p = (char) (c + 1031);        // 1105
                        else
                            throw new DecryptionException();                            // Не знаю, зачем здесь исключение, но пусть будет
                    }
                }

            }
        }
 */
