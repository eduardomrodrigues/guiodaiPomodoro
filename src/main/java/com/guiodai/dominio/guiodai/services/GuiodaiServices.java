package com.guiodai.dominio.guiodai.services;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.SslConfigurator;

public class GuiodaiServices {

	private SSLContext sslContext;

	public GuiodaiServices() {

		System.setProperty("jsse.enableSNIExtension", "false");
		
		byte[] bTrustStore = null;
		byte[] bKeyFile = null;
		try {
		
			bTrustStore = IOUtils.toByteArray(GuiodaiServices.class.getResourceAsStream("/ssl/cacerts.jks"));
			bKeyFile = IOUtils.toByteArray(GuiodaiServices.class.getResourceAsStream("/ssl/keystore.jks"));
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		SslConfigurator sslConfigurator = SslConfigurator.newInstance().trustStoreBytes(bTrustStore)
				.trustStorePassword("changeit").keyStoreBytes(bKeyFile).keyPassword("changeit");
		sslContext = sslConfigurator.createSSLContext();

	}

	public PomodoroIssue recuperarPomodoro(Long userId, Long issueId) throws PomodoroNotFoundException {

		PomodoroIssue p = new PomodoroIssue();
		try {

			Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
			WebTarget target = client.target(
					"https://www.guiodai.com/pomodoro/services/pomodoro/userid/" + userId + "/issueid/" + issueId);

			p = target.request(MediaType.APPLICATION_JSON_TYPE).get(PomodoroIssue.class);
		} catch (javax.ws.rs.NotFoundException e) {
			throw new PomodoroNotFoundException();
		}
		return p;

	}

	public void incrementarPomodoro(Long userId, Long issueId) {

		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();

		WebTarget target = client.target("https://www.guiodai.com/pomodoro/services/pomodoro");

		Form form = new Form();
		form.param("userId", "" + userId);
		form.param("issueId", "" + issueId);

		target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

	}

}
