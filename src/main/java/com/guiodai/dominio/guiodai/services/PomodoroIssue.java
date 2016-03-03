package com.guiodai.dominio.guiodai.services;

public class PomodoroIssue {

	private Long issueId;

	private Long userId;

	private Integer pomodoros;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Integer getPomodoros() {
		return pomodoros;
	}

	public void setPomodoros(Integer pomodoros) {
		this.pomodoros = pomodoros;
	}

}
