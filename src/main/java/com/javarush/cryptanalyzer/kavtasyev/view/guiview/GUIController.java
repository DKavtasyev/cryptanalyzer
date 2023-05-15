package com.javarush.cryptanalyzer.kavtasyev.view.guiview;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.functions.Transceiver;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GUIController
{
	protected ArrayList<String> openedFile;
	private ArrayList<String> normalFile;
	private ArrayList<String> resultFile;
	protected Transceiver transceiver;
	private boolean isSaved;
	private File file;
	protected GUIView guiView;

	public GUIController()
	{
		this.transceiver = new Transceiver();
	}

	public void setGUIView(GUIView guiView)
	{
		this.guiView = guiView;
	}

	protected File openFile()
	{
		ArrayList<String> openedFile = new ArrayList<>();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Открыть файл...");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовый документ", "*.txt"));
		fileChooser.setInitialDirectory(new File("src/main/java/com/javarush/cryptanalyzer/kavtasyev/txt/"));
		File file = fileChooser.showOpenDialog(guiView.getStage());
		return file;
	}

	void saveFile()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Сохранить как...");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовый документ", "*.txt"));
		fileChooser.setInitialDirectory(new File("src/main/java/com/javarush/cryptanalyzer/kavtasyev/txt/"));
		File file = fileChooser.showSaveDialog(guiView.getStage());
		if (resultFile != null)
		{
			try
			{
				transceiver.writeFile(resultFile, file.toString());
				isSaved = true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(guiView.getStage());
			alert.setTitle("Ошибка!");
			alert.setHeaderText("Файл не получен.");
			alert.setContentText("Пожалуйста запустите программу");
			alert.showAndWait();
		}
	}

	public void quit()
	{
		if (isSaved)
			System.exit(0);
		else
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(guiView.getStage());
			alert.setTitle("Файл не сохранён!");
			alert.setHeaderText("Сохранить файл перед выходом?");
			ButtonType buttonTypeYes = new ButtonType("Сохранить");
			ButtonType buttonTypeNo = new ButtonType("Не сохранять", ButtonBar.ButtonData.CANCEL_CLOSE);
			ButtonType buttonTypeCancel = new  ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeYes)
				saveFile();
			else if (result.get() == buttonTypeNo)
				System.exit(0);
			else if (result.get() == buttonTypeCancel)
				return;
		}
	}

	private void clear()
	{

	}

	void cutFile()
	{

	}

	void copyFile()
	{

	}
}
