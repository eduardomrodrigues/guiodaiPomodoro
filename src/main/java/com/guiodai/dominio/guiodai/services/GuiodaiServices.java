package com.guiodai.dominio.guiodai.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class GuiodaiServices {

	public PomodoroIssue recuperarPomodoro(Long userId, Long issueId) throws PomodoroNotFoundException {

		PomodoroIssue p = new PomodoroIssue();
		try {

			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(
					"http://www.guiodai.com/pomodoro/services/pomodoro/userid/" + userId + "/issueid/" + issueId);

			p = target.request(MediaType.APPLICATION_JSON_TYPE).get(PomodoroIssue.class);
		} catch (javax.ws.rs.NotFoundException e) {
			throw new PomodoroNotFoundException();
		}
		return p;

	}

	public void incrementarPomodoro(Long userId, Long issueId) {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://www.guiodai.com/pomodoro/services/pomodoro");

		Form form = new Form();
		form.param("userId", "" + userId);
		form.param("issueId", "" + issueId);

		target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

	}

}
