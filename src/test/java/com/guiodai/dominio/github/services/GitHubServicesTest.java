package com.guiodai.dominio.github.services;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class GitHubServicesTest {

	private static final String USUARIO_GIT_HUB = "eduardomrodrigues";

	private static final String SENHA_GIT_HUB = "B1p1000M";

	private static final String SENHA_ERRADA_GIT_HUB = "B1p1000M2";
	
	private static final String REPOSITORIO_GIT_HUB = "guiodaiPomodoro";

	@Test
	public void testRecuperarMeusRepositoriosGitHub() throws IOException {
		GitHubServices gitHub = new GitHubServices();
		Assert.assertNotNull(gitHub.recuperarRepositorios(USUARIO_GIT_HUB,
				SENHA_GIT_HUB));

	}
	
	@Test
	public void testRecuperarMeusIssuesGitHub() throws IOException {
		GitHubServices gitHub = new GitHubServices();
		Assert.assertNotNull(gitHub.recuperarIssues(USUARIO_GIT_HUB,
				SENHA_GIT_HUB, REPOSITORIO_GIT_HUB));

	}


	@Test
	public void testLoginErradoGitHub() {
		GitHubServices gitHub = new GitHubServices();
		Assert.assertFalse(gitHub.login(USUARIO_GIT_HUB, SENHA_ERRADA_GIT_HUB));

	}

	@Test
	public void testLoginCertoGitHub() {
		GitHubServices gitHub = new GitHubServices();
		Assert.assertTrue(gitHub.login(USUARIO_GIT_HUB, SENHA_GIT_HUB));

	}
	
	@Test
	public void testRecuperarUsuarioGitHub() {
		GitHubServices gitHub = new GitHubServices();
		Assert.assertNotNull(gitHub.recuperarUsuario(USUARIO_GIT_HUB, SENHA_GIT_HUB));

	}

}
