package com.javarush.cryptanalyzer.kavtasyev.view.guiview;

import com.javarush.cryptanalyzer.kavtasyev.entity.CustomData;
import com.javarush.cryptanalyzer.kavtasyev.entity.Result;

import com.javarush.cryptanalyzer.kavtasyev.functions.Transceiver;
import com.javarush.cryptanalyzer.kavtasyev.view.View;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace.DecryptWorkSpace;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace.EncryptWorkSpace;
import com.javarush.cryptanalyzer.kavtasyev.view.guiview.workspace.StatisticalWorkSpace;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GUIView extends Application implements View
{
	private Stage primaryStage;
	private CustomData customData;
	private GUIController controller;


	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Криптоанализатор");

		initRootLayout();
	}

	private void initRootLayout()
	{
		EncryptWorkSpace encryptWorkSpace = new EncryptWorkSpace();
		DecryptWorkSpace decryptWorkSpace = new DecryptWorkSpace();
		StatisticalWorkSpace statisticalWorkSpace = new StatisticalWorkSpace();
		controller = new GUIController();
		controller.setGUIView(this);
		encryptWorkSpace.setGUIView(this);
		decryptWorkSpace.setGUIView(this);
		statisticalWorkSpace.setGUIView(this);

		// Создание меню
		MenuBar mainMenu = new MenuBar();
		Menu menuFile = new Menu("Файл");
		Menu menuEdit = new Menu("Правка");
		Menu menuHelp = new Menu("Помощь");
		mainMenu.getMenus().addAll(menuFile, menuEdit, menuHelp);
		MenuItem open = new MenuItem("Открыть...");
		open.setOnAction((event) -> {
			controller.openFile();

		});
		MenuItem save = new MenuItem("Сохранить как...");
		save.setOnAction((event) -> {
			controller.saveFile();
		});
		MenuItem quit = new MenuItem("Выход");
		quit.setOnAction((event) -> {
			controller.quit();
		});
		menuFile.getItems().addAll(open, save, quit);
		MenuItem cut = new MenuItem("Вырезать");
		cut.setOnAction((event) -> {
			controller.cutFile();
		});
		MenuItem copy = new MenuItem("Копировать");
		copy.setOnAction((event) -> {
			controller.copyFile();
		});
		menuEdit.getItems().addAll(cut, copy);
		MenuItem about = new MenuItem("О программе");
		about.setOnAction((event) -> {
			// TODO написать код вызова информации о программе
		});
		menuHelp.getItems().add(about);

		// Строка состояния
		VBox statusBar = new VBox();
		Label status = new Label("");
		statusBar.getChildren().addAll(new Separator(), new HBox(status));

		// Формирование вкладок
		TabPane tabPane = new TabPane();
		Tab encryptTab = new Tab("Шифрование текста");
		Tab decryptTab = new Tab("Дешифрование подбором ключа");
		Tab statisticalTab = new Tab("Дешифрование статистическим анализом");
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabPane.getTabs().addAll(encryptTab, decryptTab, statisticalTab);
		encryptTab.setContent(encryptWorkSpace.getView());
		decryptTab.setContent(decryptWorkSpace.getView());
		statisticalTab.setContent((statisticalWorkSpace.getView()));

		// Главный слой
		BorderPane rootLayout = new BorderPane();
		rootLayout.setTop(mainMenu);
		rootLayout.setCenter(tabPane);
		rootLayout.setBottom(statusBar);

		Scene scene = new Scene(rootLayout, 1080, 600);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public GUIView getGUIView()
	{
		return this;
	}

	public Stage getStage()
	{
		return primaryStage;
	}

	public CustomData getCustomerParameters()
	{
		launch();
		return customData;
	}

	@Override
	public void printResult(Result result)
	{

	}
}
