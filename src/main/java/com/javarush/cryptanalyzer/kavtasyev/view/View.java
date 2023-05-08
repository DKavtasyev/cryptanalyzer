package com.javarush.cryptanalyzer.kavtasyev.view;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;

public interface View
{
    CustomData getParameters();


	void printResult(Result result);
}
