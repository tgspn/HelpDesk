package com.helpdesk.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.helpdesk.dao.ChamadoDAO;
import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.repository.ChamadoRepository;
import com.helpdesk.repository.ConnectionSingleton;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PrincipalController implements Initializable {

	@FXML
	private MenuBar menuBar;
	@FXML
	private Button btnClientes;
	@FXML
	private Button btnRequisicoes;
	@FXML
	private Button btnTecnicos;

	private ObservableList<Chamado> chamados;
	private ObservableList<Cliente> Clientes;// = FXCollections.observableArrayList();
	@FXML
	private ListView<Chamado> lvRequerimentos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			CheckDB();

			com.helpdesk.dao.ClienteDAO c = new ClienteDAO();

			Clientes = c.List();

			com.helpdesk.dao.ChamadoDAO cha;

			cha = new ChamadoDAO();

			chamados = cha.List();

			lvRequerimentos.setItems(chamados);
			lvRequerimentos.setCellFactory(new Callback<ListView<Chamado>, ListCell<Chamado>>() {

				@Override
				public ListCell<Chamado> call(ListView<Chamado> p) {

					ListCell<Chamado> cell = new ListCell<Chamado>() {

						@Override
						protected void updateItem(Chamado t, boolean bln) {
							super.updateItem(t, bln);
							if (t != null) {
								setText("[" + t.getId() + "] " + t.getAssunto() + " - " + t.getCategoria());
							}
						}

					};

					return cell;
				}
			});

		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
	}

	private void CheckDB() {
		File f = new File("sample.db");
		if (!f.exists()) {
			ChamadoRepository rep;
			try {
				rep = new ChamadoRepository();

				rep.CreateTable();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@FXML
	private void handleRequisicoesAction(final ActionEvent event) {
		OpenScene("CadastroRequisicoes.fxml");
	}

	@FXML
	private void handleClienteAction(final ActionEvent evet) {
		OpenScene("CadastroCliente.fxml");
	}

	@FXML
	private void handleTecnicosAction(final ActionEvent evt) {
		OpenScene("CadastroTecnicos.fxml");
	}

	@FXML
	private void handleCloseAction(final ActionEvent event) {
		Close();
	}

	private void Close() {
		try {
			ConnectionSingleton.getInstance().Close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}

	private void OpenScene(String fileName) {
		FXMLLoader f = new FXMLLoader();
		Stage stage = new Stage();
		Parent fxmlRoot;
		try {
			fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/" + fileName)));
			stage.setScene(new Scene(fxmlRoot));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
