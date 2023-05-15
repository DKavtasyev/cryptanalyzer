package com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TextsWindow
{
	protected TextArea firstText;
	protected TextArea secondText;

	public Parent getView()
	{
		// Создание примитивных элементов
		firstText = new TextArea();
		secondText = new TextArea();
		Label numberOfNormalSigns = new Label("Число знаков: 0");
		Label numberOfNormalWords = new Label("Число слов: 0");
		Label numberOfEncryptedSigns = new Label("Число знаков: 0");
		Label numberOfEncryptedWords = new Label("Число слов: 0");

		// Создание строк статистики для текстов
		VBox statsForNormalText = new VBox();
		HBox hBox1 = new HBox(numberOfNormalSigns, numberOfNormalWords);
		hBox1.setSpacing(25.0);
		statsForNormalText.getChildren().addAll(new Separator(), hBox1);
		VBox statsForEncryptedText = new VBox();
		HBox hBox2 = new HBox(numberOfEncryptedSigns, numberOfEncryptedWords);
		hBox2.setSpacing(25.0);
		statsForEncryptedText.getChildren().addAll(new Separator(), hBox2);

		// Сборка текстовых окон
		BorderPane firstTextGroup = new BorderPane();
		BorderPane secondTextGroup = new BorderPane();
		firstTextGroup.setCenter(firstText);
		firstTextGroup.setBottom(statsForNormalText);
		secondTextGroup.setCenter(secondText);
		secondTextGroup.setBottom(statsForEncryptedText);
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(firstTextGroup, secondTextGroup);

		return splitPane;
	}
}
