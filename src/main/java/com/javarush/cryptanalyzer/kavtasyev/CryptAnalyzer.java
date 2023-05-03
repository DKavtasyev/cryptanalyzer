package com.javarush.cryptanalyzer.kavtasyev;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

/*
C:\IdeaProjects\JavaRush_University\txt\CryptAnalyzer\text.txt
*/

public class CryptAnalyzer
{
    //public static String textFile;
    //public static int key;

    public static final String encryption = "-e";
    public static final String decryption = "-d";
    public static final String statisticsDecryption = "-s";
    public static ArrayList<String> prefixes = new ArrayList<>();
    public static ArrayList<String> endings = new ArrayList<>();
    public static ArrayList<String> frequentWords = new ArrayList<>();

    static
    {
        Collections.addAll(prefixes, "рас", "раз", "под", "без", "бес", "про", "от", "до", "на", "над", "за", "при", "пре", "пере", "пред", "про", "через",
                "чрез", "черес", "вос", "воз", "наи", "недо", "низ", "нис", "обес", "обез", "ана", "анти", "архи", "гипер", "гипо", "дез", "дис", "ин", "кило", "контр",
                "микро", "макро", "мега", "орто", "пара", "пост", "прото", "суб", "супер", "транс", "ультра", "экс");
        Collections.addAll(endings, "ий", "ый", "ая", "ое", "ого", "ой", "ому", "ую", "им", "ым", "ом", "а", "ы", "я", "и", "е", "ю", "у", "ей", "ем");
        Collections.addAll(frequentWords, "не", "ни", "но", "и", "в", "я", "ты", "он", "она", "они","оно", "мы", "вы", "тебя", "себя", "это", "то", "уж",
                "всё", "все", "да", "нет", "как", "или", "что", "так", "уже", "ли", "под", "над", "про", "после", "от", "даже", "дальше", "хотя", "ведь", "почему",
                "быстро", "слева", "справа", "раз", "точно", "есть", "именно", "ведь", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять");
    }

    public static void main(String[] args) throws EncryptionException, DecryptionException
    {
        String flag = args[0];

        switch (flag)
        {
            case (encryption): encrypt(args[1], args[2]); break;
            case (decryption): decrypt(args[1]); break;
            case (statisticsDecryption): analyzeStatistically(args[1], args[2]);break;
            default: System.out.println("Команда не найдена!");
        }

    }



    public static void encrypt(String unencryptedFile, String key) throws EncryptionException
    {
        String encryptedFile;
        int length = unencryptedFile.length();
        encryptedFile = unencryptedFile.substring(0, length - 4) + "_encrypted" + unencryptedFile.substring(length - 4, length);
        int keyNumber = Integer.parseInt(key);

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
    }

    public static void decrypt(String encryptedFile) throws DecryptionException
    {
        ArrayList<String> fileToDecrypt = new ArrayList<>();
        ArrayList<String> decryptedFile = new ArrayList<>();
        HashMap<Integer, Integer> correlation = new HashMap<>();
        int key;
        int correlationCoefficient;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptedFile)))
        {

            while (bufferedReader.ready())
                fileToDecrypt.add(bufferedReader.readLine());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 75; i++)
        {
            for(String line : fileToDecrypt)
            {
                StringBuilder builder = new StringBuilder();
                char[] charArray = line.toCharArray();
                for (char c : charArray)
                {
                    {
                        if (c >= 32 && c <= 34)				c = (char) (c - 32);            // 0...2
                        else if (c >= 44 && c <= 46)		c = (char) (c - 41);            // 3...5
                        else if (c == 58 || c == 59)		c = (char) (c - 52);            // 6, 7
                        else if (c == 63)					c = (char) (c - 55);            // 8
                        else if (c == 1025)					c = (char) (c - 1016);          // 9
                        else if (c >= 1040 && c <= 1103)	c = (char) (c - 1030);			// 10...73
                        else if (c == 1105)					c = (char) (c - 1031);          // 74
                        else
                        {
                            builder.append(c);
                            continue;
                        }

                        if (c < i)
                            c =+ 75;
                        char p = (char)(c - i);

                        if (p >= 0 && p <= 2)				p = (char) (c + 32);		// 32...34
                        else if (p >= 3 && p <= 5)			p = (char) (c + 41);		// 44...46
                        else if (p == 6 || p == 7)			p = (char) (c + 52);		// 58, 59
                        else if (p == 8)					p = (char) (c + 55);		// 63
                        else if (p == 9)					p = (char) (c + 1016);		// 1025
                        else if (p >= 10 && p <= 73)		p = (char) (c + 1030);		// 1040...1103
                        else if (p == 74)					p = (char) (c + 1031);		// 1105
                        else
                            throw new DecryptionException();							// Не знаю, зачем здесь исключение, но пусть будет
                    }
                }

            }
        }







    }

    public static int recognizeTheText(String text)
    {
        int correlationCoefficient = 0;



        return correlationCoefficient;
    }

    public static void analyzeStatistically(String decryptedFile, String normalFile)
    {

    }

    public static class EncryptionException extends Throwable
    {

    }

    public static class DecryptionException extends Throwable
    {

    }

	/*public static void readParameters()
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
}
