package com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace;

import com.javarush.cryptanalyzer.kavtasyev.constants.EncryptionAlphabet;
import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;
import com.javarush.cryptanalyzer.kavtasyev.functions.Encrypt;
import com.javarush.cryptanalyzer.kavtasyev.functions.Function;
import com.javarush.cryptanalyzer.kavtasyev.functions.Transceiver;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.GUIController;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.GUIView;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.File;
import java.util.ArrayList;

public class EncryptWorkSpace extends GUIController
{
	public Parent getView()
	{
		TextsWindow textsWindow = new TextsWindow();

		// Создание примитивных элементов для панели управления
		TextField encryptedFilePath = new TextField();
		encryptedFilePath.setEditable(false);
		encryptedFilePath.setMinWidth(300);
		Button start = new Button("Старт");													// Кнопка старт
		start.setPrefWidth(100);
		start.setTooltip(new Tooltip("Нажмите для начала преобразования"));
		start.setOnAction((event) -> {
			Function function = new Encrypt();

		});
		Button exit = new Button("Выход");													// Кнопка выход
		exit.setPrefWidth(100);
		exit.setTooltip(new Tooltip("Выход"));
		exit.setOnAction((event) -> {
			quit();
		});
		Label key = new Label("Ключ:");
		Spinner<Integer> keySpinner = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, EncryptionAlphabet.getAlphabetLength() - 1, 1);
		keySpinner.setEditable(true);
		keySpinner.setValueFactory(valueFactory);
		Button open = new Button("Открыть...");												// Кнопка открыть
		open.setPrefWidth(100);
		open.setTooltip(new Tooltip("Открыть файл с текстом для зашифрования..."));
		open.setOnAction((event) -> {
			File file = openFile();
			if (file != null)
			{
				try
				{
					openedFile = transceiver.readFile(file.toString());
				}
				catch (Exception e)
				{

				}
			}
			encryptedFilePath.setText(file.toString());
			StringBuilder builder = new StringBuilder();
			for(String s : openedFile)
			{
				builder.append(s + "\n");
			}
			textsWindow.firstText.setText(builder.toString());
		});
		//Label normalFilePath = new Label("Незашифрованный файл");
		Button save = new Button("Сохранить...");											// Кнопка сохранить
		save.setPrefWidth(100);
		save.setTooltip(new Tooltip("Сохранить зашифрованный файл..."));
		save.setOnAction((event) -> {

		});


		//Панель для кнопок старт, выход, выбор ключа, подпись "ключ"
		GridPane startExitButtons = new GridPane();
		startExitButtons.add(key, 1, 1);
		startExitButtons.add(keySpinner, 2, 1);
		startExitButtons.add(start, 3, 1);
		startExitButtons.add(exit, 3, 2);
		startExitButtons.setVgap(10);
		startExitButtons.setHgap(10);
		startExitButtons.setPadding(new Insets(10));

		// Панель для кнопок открыть, сохранить, текстового поля "адрес"
		GridPane openSaveButtons = new GridPane();
		openSaveButtons.add(open, 1, 1);
		openSaveButtons.add(save, 1, 2);
		openSaveButtons.add(encryptedFilePath, 2, 1);
		openSaveButtons.setVgap(10);
		openSaveButtons.setHgap(10);
		openSaveButtons.setPadding(new Insets(10));

		// Создание панели управления
		AnchorPane paneOfEncrypt = new AnchorPane();
		paneOfEncrypt.getChildren().addAll(startExitButtons, openSaveButtons);
		AnchorPane.setTopAnchor(startExitButtons, 10.0);									// Выравнивание панели кнопок старт, выход
		AnchorPane.setBottomAnchor(startExitButtons, 10.0);
		AnchorPane.setRightAnchor(startExitButtons, 10.0);
		AnchorPane.setLeftAnchor(openSaveButtons, 10.0);									// Выравнивание панели кнопок открыть, сохранить
		AnchorPane.setTopAnchor(openSaveButtons, 10.0);
		AnchorPane.setBottomAnchor(openSaveButtons, 10.0);

		// Сборка рабочего пространства
		BorderPane layout = new BorderPane();
		layout.setCenter(textsWindow.getView());
		layout.setBottom(paneOfEncrypt);

		return layout;
	}
}
