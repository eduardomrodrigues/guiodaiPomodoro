package com.guiodai.dominio.github.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.guiodai.dominio.github.dominio.GitHubHttpConnection;
import com.guiodai.dominio.github.dominio.Issue;
import com.guiodai.dominio.github.dominio.Repositorio;
import com.guiodai.dominio.github.dominio.UsuarioGithub;
import com.guiodai.dominio.github.exceptions.BadRequestException;
import com.guiodai.dominio.github.exceptions.UnauthorizedException;

public class GitHubServices extends GitHubHttpConnection {

	public boolean login(String usuario, String senha) {

		// FIXME: Drop on production

		return true;

		// try {
		// super.gitGet("https://api.github.com/user", usuario, senha);
		// } catch (UnauthorizedException e) {
		// return false;
		// } catch (BadRequestException e) {
		// e.printStackTrace();
		// }
		// return true;

	}

	public UsuarioGithub recuperarUsuario(String usuario, String senha) {

		UsuarioGithub usuarioGithub = new UsuarioGithub();

		try {
			List<JSONObject> jsons = super.gitGet("https://api.github.com/user", usuario, senha);
			for (JSONObject o : jsons) {
				usuarioGithub.setId(o.getLong("id"));
				usuarioGithub.setLogin(o.getString("login"));
				usuarioGithub.setAvatarUrl(o.getString("avatar_url"));
				usuarioGithub.setName(o.getString("name"));

			}

		} catch (UnauthorizedException e) {
			return null;
		} catch (BadRequestException e) {
			e.printStackTrace();
		}
		return usuarioGithub;

	}

	/**
	 * @param usuario
	 * @param senha
	 * @return
	 * @throws IOException
	 * 
	 *             https://developer.github.com/v3/repos/#list-your-repositories
	 */
	public List<Repositorio> recuperarRepositorios(String usuario, String senha) {

		List<Repositorio> repositorios = new ArrayList<Repositorio>();
		try {
			List<JSONObject> jsons = super.gitGet("https://api.github.com/user/repos", usuario, senha);
			for (JSONObject o : jsons) {
				Repositorio repo = new Repositorio();
				repo.setNome((String) o.get("name"));

				repositorios.add(repo);
			}
		} catch (UnauthorizedException | BadRequestException e) {
			e.printStackTrace();
		}

		return repositorios;
	}

	public List<Issue> recuperarIssues(String usuario, String senha, String repo) {

		List<Issue> issues = new ArrayList<Issue>();

		try {
			List<JSONObject> jsons = super.gitGet("https://api.github.com/repos/" + usuario + "/" + repo + "/issues",
					usuario, senha);

			for (JSONObject o : jsons) {
				Issue issue = new Issue();
				issue.setId(o.getLong("id"));
				issue.setNumber(o.getInt("number"));
				issue.setTitle(o.getString("title"));

				issues.add(issue);

			}

		} catch (UnauthorizedException | BadRequestException e) {
			e.printStackTrace();
		}

		return issues;
	}

}
