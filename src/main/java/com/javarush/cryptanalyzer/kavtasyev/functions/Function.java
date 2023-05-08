package com.javarush.cryptanalyzer.kavtasyev.functions;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;

public abstract class Function implements Timeable
{
	Transceiver transceiver;
	Service service;

	public Function()
	{
		transceiver = new Transceiver();
		service = new Service();
	}

	public abstract Result execute(CustomData customData);
}
