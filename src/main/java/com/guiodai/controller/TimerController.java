package com.guiodai.controller;

import java.time.LocalTime;
import java.util.List;

import com.guiodai.dominio.github.dominio.Issue;
import com.guiodai.dominio.github.services.GitHubServices;
import com.guiodai.dominio.guiodai.services.GuiodaiServices;
import com.guiodai.dominio.guiodai.services.PomodoroIssue;
import com.guiodai.dominio.guiodai.services.PomodoroNotFoundException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

public class TimerController {

	private static final AudioClip ALERT_AUDIOCLIP = new AudioClip(TimerController.class.getResource("/wav/alert.wav").toString());
	
	private Issue issue;

	@FXML
	private Label labelTimer;

	@FXML
	private Label clock;

	private boolean cronometroLigado = false;

	private boolean breakTime = false;

	private GitHubServices gitHub;

	private IssuesListController issueController;

	private Task task;

	private Thread th;

	private static final int POMODORO_TIME_FINISHED = 25 * 60;

	private static final int POMODORO_BREAK_TIME = 5 * 60;

	private String repoName;

	private Long userId;

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

		task = new Task<Void>() {
			@Override
			public Void call() throws Exception {

				int i = (breakTime) ? POMODORO_BREAK_TIME : POMODORO_TIME_FINISHED;

				while (cronometroLigado) {
					final int finalI = i;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							String formatTime = formatTime(finalI);
							clock.setText("" + formatTime);

							if (finalI == 0) {
								if (!breakTime) {
									inicializarBreak();
								} else {
									terminarPomodoro();
								}
							}
						}
					});
					i--;
					Thread.sleep(1000);
				}
				return null;
			}
		};
		th = new Thread(task);
		th.setDaemon(true);
		th.start();

	}

	private void terminarPomodoro() {
		breakTime = false;
		
		TimerController.ALERT_AUDIOCLIP.play();

		this.finalizarCronograma();
		labelTimer.getStyleClass().add("fonte-titulo");
		labelTimer.getStyleClass().remove("fonte-break");
		labelTimer.setText(issue.getTitle());

		GuiodaiServices services = new GuiodaiServices();
		services.incrementarPomodoro(userId, issue.getId());

		labelTimer.setText(issue.getTitle() + "  #" + getNumeroPomodoros());

		this.refreshTelaPai();

	}

	private Integer getNumeroPomodoros() {
		GuiodaiServices services = new GuiodaiServices();
		try {
			PomodoroIssue pIssue = services.recuperarPomodoro(userId, issue.getId());
			return pIssue.getPomodoros();
		} catch (PomodoroNotFoundException e) {
			return 0;
		}
	}

	private String formatTime(Integer seconds) {
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
		String timeFormat = (timeOfDay.toString().length() <= 5) ? timeOfDay.toString() + ":00" : timeOfDay.toString();
		return timeFormat;
	}

	@FXML
	protected void handleStopButtonAction(ActionEvent event) {
		this.finalizarCronograma();
		this.inicializar();
	}

	private void finalizarCronograma() {
		cronometroLigado = false;
		th.interrupt();
		task.cancel();
	}

	public void inicializarBreak() {

		labelTimer.setText("TAKE A BREAK!");
		labelTimer.getStyleClass().remove("fonte-titulo");
		labelTimer.getStyleClass().add("fonte-break");

		breakTime = true;

		this.finalizarCronograma();
		clock.setText(this.formatTime(POMODORO_BREAK_TIME));

	}

	public void inicializar() {

		labelTimer.setText(issue.getTitle() + "  #" + getNumeroPomodoros());

		clock.setText(this.formatTime(POMODORO_TIME_FINISHED));

	}

	private void setPomodoroNumbers(List<Issue> issues) {

		GuiodaiServices services = new GuiodaiServices();

		for (Issue i : issues) {

			PomodoroIssue p = new PomodoroIssue();
			try {
				p = services.recuperarPomodoro(userId, i.getId());
			} catch (PomodoroNotFoundException e) {
				p.setIssueId(i.getId());
				p.setPomodoros(0);
				p.setUserId(userId);

			}
			i.setPomodoros(p.getPomodoros());
		}
	}

	private void refreshTelaPai() {
		issueController.refreshTabela(repoName);

	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setIssueController(IssuesListController issueController) {
		this.issueController = issueController;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

}
