package com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace;

import com.javarush.cryptanalyzer.kavtasyev.view.guiview.GUIController;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.GUIView;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatisticalWorkSpace extends GUIController
{



	public Parent getView()
	{
		TextsWindow textsWindow = new TextsWindow();

		// Создание примитивных элементов для панели управления
		Button start = new Button("Старт");													// Кнопка старт
		start.setPrefWidth(100);
		start.setTooltip(new Tooltip("Нажмите для начала преобразования"));
		start.setOnAction((event) -> {

		});
		Button exit = new Button("Выход");															// Кнопка выход
		exit.setPrefWidth(100);
		exit.setTooltip(new Tooltip("Выход"));
		exit.setOnAction((event) -> {
			quit();
		});
		Button openEncrypted = new Button("Открыть...");												// Кнопка открыть зашифрованный файл
		openEncrypted.setPrefWidth(100);
		openEncrypted.setTooltip(new Tooltip("Открыть файл с зашифрованным текстом для расшифрования..."));
		openEncrypted.setOnAction((event) -> {

		});
		Button openNormal = new Button("Открыть...");												// Кнопка открыть нормальный файл для статистики
		openNormal.setPrefWidth(100);
		openNormal.setTooltip(new Tooltip("Открыть файл с незашифрованным текстом для вычисления статистики..."));
		openNormal.setOnAction((event) -> {

		});
		Button save = new Button("Сохранить...");											// Кнопка сохранить
		save.setPrefWidth(100);
		save.setTooltip(new Tooltip("Сохранить расшифрованный файл..."));
		save.setOnAction((event) -> {

		});
		TextField decryptedFilePath = new TextField();
		decryptedFilePath.setEditable(false);
		decryptedFilePath.setMinWidth(300);
		TextField normalFilePath = new TextField();
		decryptedFilePath.setEditable(false);
		decryptedFilePath.setMinWidth(300);

		//Панель для кнопок старт, выход
		GridPane startExitButtons = new GridPane();
		startExitButtons.add(start, 3, 1);
		startExitButtons.add(exit, 3, 2);
		startExitButtons.setVgap(10);
		startExitButtons.setHgap(10);
		startExitButtons.setPadding(new Insets(10));

		// Панель для кнопок открыть зашифрованный файл, открыть нормальный файл, сохранить, текстового поля "адрес"
		GridPane openSaveButtons = new GridPane();
		openSaveButtons.add(openEncrypted, 1, 1);
		openSaveButtons.add(openNormal, 1, 2);
		openSaveButtons.add(decryptedFilePath, 2, 1);
		openSaveButtons.add(normalFilePath, 2, 2);
		openSaveButtons.add(save, 3, 1);
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
