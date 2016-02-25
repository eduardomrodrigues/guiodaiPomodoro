package com.guiodai.controller;

import com.guiodai.dominio.Chronometro;
import com.guiodai.dominio.github.dominio.Issue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimerController {

	private Issue issue;

	@FXML
	private Label labelTimer;

	@FXML
	private Label hourClock;

	@FXML
	private Label minuteClock;

	@FXML
	private Label secondClock;
	
	private Chronometro chronometro = new Chronometro();

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@FXML
	protected void handlePlayButtonAction(ActionEvent event) {
		chronometro.run();
	}

	@FXML
	protected void handleStopButtonAction(ActionEvent event) {
		chronometro.stop();
	}

	public void inicializar() {

		labelTimer.setText(issue.getTitle());

		hourClock.setText("00");
		minuteClock.setText("00");
		secondClock.setText("00");

	}

}
