package com.guiodai.controller;

import java.time.LocalTime;

import com.guiodai.dominio.Cronometro;
import com.guiodai.dominio.github.dominio.Issue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.concurrent.Task;

public class TimerController {

	private Issue issue;

	@FXML
	private Label labelTimer;

	@FXML
	private Label clock;

	private boolean cronometroLigado = false;

	private Cronometro cronometro = new Cronometro();

	private Thread th;

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@FXML
	protected void handlePlayButtonAction(ActionEvent event) {
		cronometroLigado = true;
		this.ligarCronometro();
	}

	private void ligarCronometro() {

		Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				int i = 0;
				while (cronometroLigado) {
					final int finalI = i;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							clock.setText("" + formatTime(finalI));
						}
					});
					i++;
					Thread.sleep(1000);
				}
				return null;
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

	}

	private String formatTime(Integer seconds) {
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
		String timeFormat = (timeOfDay.toString().length() <= 5) ? timeOfDay.toString() + ":00" : timeOfDay.toString();
		return timeFormat;
	}

	@FXML
	protected void handleStopButtonAction(ActionEvent event) {
		cronometroLigado = false;
	}

	public void inicializar() {

		labelTimer.setText(issue.getTitle());

		clock.setText("00:00:00");

	}

}
