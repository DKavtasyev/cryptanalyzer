package com.javarush.cryptanalyzer.kavtasyev.view;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.constants.FilePaths;
import com.javarush.cryptanalyzer.kavtasyev.constants.Mode;
import com.javarush.cryptanalyzer.kavtasyev.constants.Conversation;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.repository.ResultCode;

import java.io.*;
import java.util.Random;

public class ConsoleView implements View
{
	private CustomData customData;
	@Override
	public CustomData getCustomerParameters()
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
		{
			System.out.println(Conversation.GREETINGS);

			String mode;
			String str1;
			String str2;


			while (true)
			{
				mode = reader.readLine();
				if (mode.equals(Conversation.HELP))
				{
					System.out.println(Conversation.HELP_TEXT);
					continue;
				}

				if (mode.equals(Mode.ENCRYPT) || mode.equals(Mode.DECRYPT) || mode.equals(Mode.BRUTEFORCE_DECRYPT) || mode.equals(Mode.STATISTICAL_DECRYPT) || mode.equals(Mode.PRINT_ALPHABET))
					break;
				System.out.println(Conversation.INEXISTENT_COMMAND);
			}

			switch (mode)
			{
				case (Mode.ENCRYPT) ->
				{
					System.out.println(Conversation.CONVERSATION_ABOUT_ENCRYPT[0]);
					while (true)
					{
						str1 = reader.readLine();
						if (str1.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str1.equals(""))
						{
							str1 = FilePaths.INPUT_DEFAULT_FILE_FOR_ENCRYPT;
							System.out.println(Conversation.CONVERSATION_ABOUT_ENCRYPT[3]);
						}

						else if (!str1.endsWith(Conversation.PROPER_FILE_EXTENSION))
						{
							System.out.println(Conversation.WRONG_FILE_PATH_FOR_ENCRYPT);
							continue;
						}

						File file = new File(str1);

						if (file.exists())
							break;
						System.out.println(Conversation.INEXISTENT_FILE);
					}
					System.out.println(Conversation.CONVERSATION_ABOUT_ENCRYPT[1]);
					while (true)
					{
						str2 = reader.readLine();
						if (str2.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str2.equals(""))
						{
							Random r = new Random();
							str2 = String.valueOf(r.nextInt(1, 87));
							System.out.println(Conversation.ENCRYPT_KEY_HAS_SET + str2);
							break;
						}

						try
						{
							int n = Integer.parseInt(str2);
							while (n < 0)
								n += EncryptionAlphabet.getAlphabetLength();
							str2 = String.valueOf(n);
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println(Conversation.INCORRECT_KEY);
						}

					}
					if(str1.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_ENCRYPT))
						System.out.println(Conversation.CONVERSATION_ABOUT_ENCRYPT[4]);
					else
						System.out.println(Conversation.CONVERSATION_ABOUT_ENCRYPT[2]);
					customData = new CustomData(mode, str1, str2);
				}
				case (Mode.DECRYPT) ->
				{
					System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[0]);
					while (true)
					{
						str1 = reader.readLine();
						if (str1.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str1.equals(""))
						{
							str1 = FilePaths.INPUT_DEFAULT_FILE_FOR_DECRYPT_WITH_KEY;
							System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[1]);
						}

						else if (!str1.endsWith(Conversation.PROPER_FILE_EXTENSION))
						{
							System.out.println(Conversation.WRONG_FILE_PATH_FOR_ENCRYPT);
							continue;
						}

						File file = new File(str1);

						if (file.exists())
							break;
						System.out.println(Conversation.INEXISTENT_FILE);
					}
					System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[2]);
					while (true)
					{
						str2 = reader.readLine();
						if (str2.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str2.equals(""))
						{
							System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[3]);
							continue;
						}

						try
						{
							int n = Integer.parseInt(str2);
							while (n < 0)
								n += EncryptionAlphabet.getAlphabetLength();
							str2 = String.valueOf(n);
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println(Conversation.INCORRECT_KEY);
						}
					}
					if(str1.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_DECRYPT_WITH_KEY))
						System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[4]);
					else
						System.out.println(Conversation.CONVERSATION_ABOUT_DECRYPT_WITH_KEY[5]);
					customData = new CustomData(mode, str1, str2);
				}
				case (Mode.BRUTEFORCE_DECRYPT) ->
				{
					System.out.println(Conversation.CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT[0]);
					while (true)
					{
						str1 = reader.readLine();
						if (str1.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str1.equals(""))
						{
							str1 = FilePaths.INPUT_DEFAULT_FILE_FOR_BRUTEFORCE_DECRYPT;
							File file = new File(str1);
							if (file.exists())
								System.out.println(Conversation.CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT[2]);
							else
							{
								System.out.println(Conversation.CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT[3]);
								continue;
							}

						}
						else if (!str1.endsWith(Conversation.PROPER_FILE_EXTENSION))
						{
							System.out.println(Conversation.WRONG_FILE_PATH_FOR_DECRYPT);
							continue;
						}
						File file = new File(str1);

						if (file.exists())
							break;
						System.out.println(Conversation.INEXISTENT_FILE);
					}
					if (str1.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_BRUTEFORCE_DECRYPT))
						System.out.println(Conversation.CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT[4]);
					else
						System.out.println(Conversation.CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT[1]);
					customData = new CustomData(mode, str1);
				}
				case (Mode.STATISTICAL_DECRYPT) ->
				{
					System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[0]);
					while (true)
					{
						str1 = reader.readLine();
						if (str1.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str1.equals(""))
						{
							str1 = FilePaths.INPUT_DEFAULT_FILE_FOR_STATISTICAL_DECRYPT;
							File file = new File(str1);
							if (file.exists())
								System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[3]);
							else
							{
								System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[5]);
								continue;
							}
						}
						else if (!str1.endsWith(Conversation.PROPER_FILE_EXTENSION))
						{
							System.out.println(Conversation.WRONG_FILE_PATH_FOR_DECRYPT);
							continue;
						}

						File file = new File(str1);

						if (file.exists())
							break;
						System.out.println(Conversation.INEXISTENT_FILE);
					}
					System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[1]);
					while (true)
					{
						str2 = reader.readLine();
						if (str2.equals(Conversation.HELP))
						{
							System.out.println(Conversation.HELP_TEXT);
							continue;
						}
						else if (str2.equals(""))
						{
							str2 = FilePaths.DEFAULT_REFERENCE_FILE;
							System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[4]);
						}

						else if (!str2.endsWith(Conversation.PROPER_FILE_EXTENSION))
						{
							System.out.println(Conversation.WRONG_PATH_TO_REFERENCE_FILE);
							continue;
						}

						File file = new File(str2);

						if (file.exists())
							break;
						System.out.println(Conversation.INEXISTENT_FILE);
					}
					if (str1.equals(FilePaths.INPUT_DEFAULT_FILE_FOR_BRUTEFORCE_DECRYPT))
						System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[6]);
					else
						System.out.println(Conversation.CONVERSATION_ABOUT_STATISTICS_DECRYPT[2]);
					customData = new CustomData(mode, str1, str2);
				}
				case (Mode.PRINT_ALPHABET) ->
				{
					System.out.println(Conversation.SERVICE_INFORMATION);
					customData = new CustomData(mode);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return customData;
	}

	@Override
	public void printResult(Result result)
	{
		switch (result.getResultCode())
		{
			case OK -> System.out.println(ResultCode.OK.getResultMessage() + (result.getTime() == null ? "" : result.getTime()));
			case ERROR -> System.out.println(ResultCode.ERROR.getResultMessage() + result.getException().getMessage());
		}
	}
}
