package com.guiodai.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.apache.commons.lang3.StringUtils;

import com.guiodai.dominio.github.services.GitHubServices;

public class LoginController {

	@FXML
	private Text actiontarget;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField passwordField;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		if (!this.isFormValid()) {
			return;
		}

		GitHubServices services = new GitHubServices();

		if (!services.login(userName.getText(), passwordField.getText())) {
			actiontarget.setText("Username or password is invalid");
			return;
		}

		actiontarget.setText("");
		Stage stageIssuesList = (Stage) userName.getScene().getWindow();
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/issuesList.fxml"));
			Parent issueListParent = fxmlLoader.load();
			
			IssuesListController issuesController = fxmlLoader.getController();
			issuesController.setUsuario(userName.getText());
			issuesController.setSenha(passwordField.getText());
			issuesController.carregarComboRepositorios();
			
			Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
			Scene issueListScene = new Scene(issueListParent, rectangle2D.getWidth() / 2, rectangle2D.getHeight() / 2);
			stageIssuesList.setX(rectangle2D.getMinX());
			stageIssuesList.setY(rectangle2D.getMinY());
			stageIssuesList.setScene(issueListScene);
			stageIssuesList.show();
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean isFormValid() {

		if (StringUtils.isBlank(userName.getText())
				&& StringUtils.isBlank(passwordField.getText())) {
			actiontarget.setText("Incorrect username and password");
			return false;
		}

		if (StringUtils.isBlank(userName.getText())) {
			actiontarget.setText("Incorrect username");
			return false;
		}

		if (StringUtils.isBlank(passwordField.getText())) {
			actiontarget.setText("Incorrect password");
			return false;
		}

		return true;

	}

}
