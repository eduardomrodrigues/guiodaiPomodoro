package com.guiodai.controller;

import com.guiodai.dominio.github.dominio.Issue;
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
	
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public void inicializar(){
		
		labelTimer.setText(issue.getTitle());
		
		hourClock.setText("00");
		minuteClock.setText("00");
		secondClock.setText("00");
		
		
	}
	
}
