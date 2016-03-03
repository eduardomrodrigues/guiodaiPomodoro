package com.guiodai.controller;

import java.io.IOException;
import java.util.List;

import org.omg.IOP.MultipleComponentProfileHelper;

import com.guiodai.dominio.github.dominio.Issue;
import com.guiodai.dominio.github.dominio.Repositorio;
import com.guiodai.dominio.github.dominio.UsuarioGithub;
import com.guiodai.dominio.github.services.GitHubServices;
import com.guiodai.dominio.guiodai.services.GuiodaiServices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class IssuesListController {

	@FXML
	void initialize() {
		gitHub = new GitHubServices();
		dataIssue = FXCollections.observableArrayList();
		columnTitle.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
		columnPomodoroNumber.setCellValueFactory(new PropertyValueFactory<Issue, Integer>("pomodoros"));
		this.adicionarTableViewListeners();
	}

	private GitHubServices gitHub;

	private String usuario;

	private String senha;
	
	private UsuarioGithub usuarioGithub;

	@FXML
	private ComboBox<Repositorio> comboRepositorio;

	@FXML
	private TableView<Issue> tableViewIssues;

	@FXML
	private TableColumn columnTitle;
	
	@FXML
	private TableColumn columnPomodoroNumber;


	private ObservableList<Issue> dataIssue;

	private void adicionarTableViewListeners() {
		tableViewIssues.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() > 1) {
					abrirTimer(tableViewIssues.getSelectionModel().getSelectedItem());
				}
			}
		});
	}

	private void abrirTimer(Issue issue) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/timer.fxml"));
			Parent timerParent = fxmlLoader.load();

			TimerController timerController = fxmlLoader.getController();
			timerController.setIssue(issue);
			timerController.inicializar();
			
			Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(comboRepositorio.getScene().getWindow());
			stage.setTitle("Pomodoro for issue #" + issue.getId());
			stage.setScene(new Scene(timerParent, 1250, 550));
			stage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregarComboRepositorios() {
		GitHubServices services = new GitHubServices();

		List<Repositorio> repositorios = services.recuperarRepositorios(usuario, senha);
		ObservableList<Repositorio> observableRepositoriosList = FXCollections.observableArrayList(repositorios);
		comboRepositorio.setItems(observableRepositoriosList);

	}

	public void adicionarComboListeners() {

		comboRepositorio.valueProperty().addListener(new ChangeListener<Repositorio>() {

			@Override
			public void changed(ObservableValue<? extends Repositorio> observable, Repositorio oldValue,
					Repositorio newValue) {

				dataIssue.clear();

				List<Issue> issues = gitHub.recuperarIssues(usuario, senha, newValue.getNome());
				setPomodoroNumbers(issues);
				dataIssue.addAll(issues);
				tableViewIssues.setItems(dataIssue);

			}
		});

	}

	private void setPomodoroNumbers(List<Issue> issues){
		
		GuiodaiServices services = new GuiodaiServices();
		
		for(Issue i : issues){
			i.setPomodoros(services.recuperarPomodoro(usuarioGithub.getId(), i.getId()).getPomodoros());
		}
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

	public void setUsuarioGithub(UsuarioGithub usuarioGithub) {
		this.usuarioGithub = usuarioGithub;
	}

}
