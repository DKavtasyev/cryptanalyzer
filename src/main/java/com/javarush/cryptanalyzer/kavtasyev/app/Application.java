package com.javarush.cryptanalyzer.kavtasyev.app;

import com.javarush.cryptanalyzer.kavtasyev.controller.Controller;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.functions.Function;
import com.javarush.cryptanalyzer.kavtasyev.repository.FunctionCode;

import static com.javarush.cryptanalyzer.kavtasyev.constants.Mode.*;

public class Application
{
	private Controller controller;

	public Application(Controller controller)
	{
		this.controller = controller;
	}

	public Result run()
	{
		CustomData customData = controller.getView().getCustomerParameters();
		String mode = customData.getFlag();
		Function function = getFunction(mode);
		return function.execute(customData);
	}

	private Function getFunction(String mode)
	{
		return switch (mode)
				{
					case ENCRYPT -> FunctionCode.ENCRYPT.getFunction();
					case BRUTEFORCE_DECRYPT -> FunctionCode.BRUTEFORCEDECRYPT.getFunction();
					case STATISTICAL_DECRYPT -> FunctionCode.STATISTICALDECRYPT.getFunction();
					case PRINT_ALPHABET -> FunctionCode.PRINT_ALPHABET.getFunction();
					default -> null;
				};
	}

	public void printResult(Result result)
	{
		controller.getView().printResult(result);
	}
}
