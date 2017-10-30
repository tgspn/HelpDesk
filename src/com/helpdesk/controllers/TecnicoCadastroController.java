package com.helpdesk.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;;

public class TecnicoCadastroController implements Initializable {
	
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnNovo;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnBuscar;
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtBusca;	
	@FXML
	private TableView dgvCliente;
	
	
	
	private ClienteDAO dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new ClienteDAO();
	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {

		dao.Insert(new Cliente(0, txtNome.getText(), txtTelefone.getText(), txtEmail.getText()));
		((Parent) (event.getSource())).getScene().getWindow().hide();
	}
}
