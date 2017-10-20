package com.helpdesk.main;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(Main.class, args);

	}

	@Override
	public void start(final Stage stage) throws Exception {
		FXMLLoader f = new FXMLLoader();
		final Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/principal.fxml")));
		stage.setScene(new Scene(fxmlRoot));
		stage.show();
		
	}

}
