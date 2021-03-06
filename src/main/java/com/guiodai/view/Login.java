package com.guiodai.view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxmls/login.fxml"));
		
		Scene scene = new Scene(root, 400, 275);
		
		stage.setTitle("Pomodoro");
		stage.setScene(scene);
		stage.show();
		
	}

}
