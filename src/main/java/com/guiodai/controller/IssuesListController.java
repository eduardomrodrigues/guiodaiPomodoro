package com.guiodai.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import com.guiodai.dominio.github.dominio.Repositorio;
import com.guiodai.dominio.github.services.GitHubServices;

public class IssuesListController{

	private String usuario;

	private String senha;

	@FXML
	private ComboBox<Repositorio> comboRepositorio;
	
	public void carregarComboRepositorios() {
		GitHubServices services = new GitHubServices();

		List<Repositorio> repositorios = services.recuperarRepositorios(
				usuario, senha);
		ObservableList<Repositorio> observableRepositoriosList  = FXCollections.observableArrayList(repositorios);
		comboRepositorio.setItems(observableRepositoriosList);

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
