package com.javarush.cryptanalyzer.kavtasyev.controller;

import com.javarush.cryptanalyzer.kavtasyev.view.View;

public class Controller
{
	private final View view;

	public Controller(View view)
	{
		this.view = view;
	}

	public View getView()
	{
		return view;
	}
}
