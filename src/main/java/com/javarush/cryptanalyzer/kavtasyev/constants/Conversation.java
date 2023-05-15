package com.javarush.cryptanalyzer.kavtasyev.constants;

public class Conversation
{
	public static final String GREETINGS =
			"""
			Привет Александр! Посмотри мою программу для итогового проекта. Я старался).

			Она умеет кодировать текст в формате текстового файла .txt по шифру Цезаря с любым
			значением ключа. Для этого необходимо ввести флаг "-e".

			Так же она умеет безошибочно декодировать закодированный текст по шифру Цезаря с
			условием использования такого же алфавита. Для этого введи флаг "-d".

			Можно так же попробовать декодировать закодированный текст с помощью статистического
			анализа, но тексты требуются большие (не хватило и полмиллиона символов!), вычисления происходят
			долго (у меня - мин пять!), да и результат сомнителен. Но если ради прикола решишь попробовать -
			тогда не забудь указать флаг "-s".

			И если что, то ты вместо параметров в любой момент можешь написать команду help для вывода справки
			по структуре команд.""";

	public static final String[] CONVERSATION_ABOUT_ENCRYPT = {
			"""
			Ты выбрал функцию шифрования текста. Хорошо. Теперь нужно указать путь к существующему
			текстовому файлу, который будем шифровать. Либо можно ничего не указывать, в папке проекта txt уже
			имеется файлик для примера под именем Pushkin.txt, над которым будем экспериментировать.""",

			"""
			Отлично, сущесвующий файл указан, теперь нужно указать криптографический ключ, используя который
			программа зашифрует текст из указанного файла. Можно его так же не указывать, программа сгерерирует
			подходящий ключ сама и сообщит значение.""",

			"""
			Хорошо, параметры введены корректно. Зашифрованный файл будет иметь тот же адрес и имя, но с пометкой
			_encrypted. Программа приступает к работе.
			""",

			"Работа программы будет показана на файле для примера Pushkin.txt из папки проекта txt."};

	public static final String[] CONVERSATION_ABOUT_BRUTE_FORCE_DECRYPT = {
			"""
			Ты выбрал функцию дешифрования методом BruteForce. Отлично. Теперь нужно указать путь
			к существующему текстовому файлу, который будем дешифровывать. Либо можно ничего не указывать,
			в проекте уже имеется зашифрованный файлик для примера, над которым будем экспериментировать.""",

			"""
			Отлично, сущесвующий файл указан, программа уже приступила к выполнению. Зашифрованный файл будет
			находиться в той же папке и иметь то же имя, но с пометкой _decrypted.
			""",

			"Работа программы будет показана на примере файла из папки txt под названием unknown_cyphered_text.txt."};

	public static final String[] CONVERSATION_ABOUT_STATISTICS_DECRYPT = {
			"""
			Ты выбрал функцию статистического анализа текста. Ну что ж, посмотрим, что получится.
			Теперь нужно указать путь к существующему текстовому файлу, который будем дешифровывать. Либо можно
			ничего не указывать, в проекте уже имеется зашифрованный файлик для примера, над которым будем
			экспериментировать.""",

			"""
			Отлично, существующий файл с зашифрованным содержимым указан, теперь необходимо указать файл,
			содержащий незашифрованный текст на русском языке, на основании которого будет происходить
			расшифровка. Или нажми Enter для расшифрования на основе готового шаблона из папки txt проекта.""",

			"""
			Оба файла указаны, и программа приступила к выполнению. Результат будет находиться в той же папке и
			иметь то же имя, но с пометкой _decryptedstatistically.
			""",

			"""
			Работа программы будет показана на примере зашифрованного файла из папки txt под названием
			Dostoevskiy_encrypted_key=5.
			""",

			"Для расшифрования принят пример текста из папки txt проекта под названием Gogol_normal.txt."};

	public static final String HELP = "help";

	public static final String HELP_TEXT =
			"""
			[-e][Файл для шифрования][ключ]\t\t\t\t\t команды для шифрования файлов.
			[-d][Файл для дешифрования]\t\t\t\t\t\t команды для дешифрования файлов.
			[-s][Файл для дешифрования][Нормальный файл]\t команды для статистического анализа файлов.
			""";

	public static final String PROPER_FILE_EXTENSION = ".txt";

	public static final String INEXISTENT_COMMAND = "Несуществующая команда. Повтори попытку";
	public static final String WRONG_FILE_PATH_FOR_ENCRYPT = "Это не путь к текстовому файлу. Введи пожалуйста путь к текстовому файлу, который будем зашифровывать.";
	public static final String WRONG_FILE_PATH_FOR_DECRYPT = "Это не путь к текстовому файлу. Введи пожалуйста путь к текстовому файлу, который будем расшифровывать.";
	public static final String WRONG_PATH_TO_REFERENCE_FILE = "Это не путь к текстовому файлу. Введи пожалуйста путь к незашифрованному текстовому файлу, который послужит опорным для дешифрования.";
	public static final String INEXISTENT_FILE = "Такого файла не существует. Повтори попытку";
	public static final String ENCRYPT_KEY_HAS_SET = "Шифровальный ключ сгенерирован автоматически и равен ";
	public static final String INCORRECT_KEY = "Некорректный ввод. Пожалуйста введи корректный ключ.";
	public static final String SERVICE_INFORMATION = "Печать в файл CharTable.txt служебной информации...";
}
