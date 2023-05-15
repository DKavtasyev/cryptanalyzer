package com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace;

import com.javarush.cryptanalyzer.kavtasyev.entity.LetterOccurrence;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TextsTableWindow
{
	public Parent getView()
	{
		// Создание примитивных элементов
		TextArea normalText = new TextArea();
		TextArea encryptedText = new TextArea();
		Label numberOfNormalSigns = new Label("Число знаков: 0");
		Label numberOfNormalWords = new Label("Число слов: 0");
		Label numberOfEncryptedSigns = new Label("Число знаков: 0");
		Label numberOfEncryptedWords = new Label("Число слов: 0");
		TableView<LetterOccurrence> table = new TableView();

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
		BorderPane decryptedTextGroup = new BorderPane();
		BorderPane encryptedTextGroup = new BorderPane();
		encryptedTextGroup.setCenter(encryptedText);
		encryptedTextGroup.setBottom(statsForEncryptedText);
		decryptedTextGroup.setCenter(normalText);
		decryptedTextGroup.setBottom(statsForNormalText);

		GridPane gridPane = new GridPane();
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.getColumnConstraints().add(new ColumnConstraints(15));
		gridPane.getColumnConstraints().add(new ColumnConstraints());
		gridPane.add(encryptedTextGroup, 1, 1	);
		gridPane.add(table, 2, 1	);
		gridPane.add(decryptedTextGroup, 3, 1);



		return gridPane;
	}
}
