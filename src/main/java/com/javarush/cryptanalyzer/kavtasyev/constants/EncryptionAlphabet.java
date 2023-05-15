package com.javarush.cryptanalyzer.kavtasyev.constants;

public class EncryptionAlphabet
{
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = " !\",-.:;?—…";
    private static final String CAPITAL_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String LOWERCASE_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ALPHABET_STRING = DIGITS + PUNCTUATION + CAPITAL_LETTERS + LOWERCASE_LETTERS;
    private static final String LOWERCASE_ALPHABET_STRING = DIGITS + PUNCTUATION + LOWERCASE_LETTERS;
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final char[] LOWERCASE_ALPHABET = LOWERCASE_ALPHABET_STRING.toCharArray();

    static
    {
        for (int i = 0; i < ALPHABET.length - 1; i++)
        {
            for (int j = 0; j < ALPHABET.length - i - 1; j++)
            {
                if (ALPHABET[j] > ALPHABET[j + 1])
                {
                    char temp = ALPHABET[j];
                    ALPHABET[j] = ALPHABET[j + 1];
                    ALPHABET[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < LOWERCASE_ALPHABET.length - 1; i++)
        {
            for (int j = 0; j < LOWERCASE_ALPHABET.length - i - 1; j++)
            {
                if (LOWERCASE_ALPHABET[j] > LOWERCASE_ALPHABET[j + 1])
                {
                    char temp = LOWERCASE_ALPHABET[j];
                    LOWERCASE_ALPHABET[j] = LOWERCASE_ALPHABET[j + 1];
                    LOWERCASE_ALPHABET[j + 1] = temp;
                }
            }
        }
    }

    public static char[] getAlphabet()
    {
        return ALPHABET;
    }

    public static char[] getLowercaseAlphabet()
    {
        return LOWERCASE_ALPHABET;
    }

    public static int getAlphabetLength()
    {
        return ALPHABET.length;
    }
}