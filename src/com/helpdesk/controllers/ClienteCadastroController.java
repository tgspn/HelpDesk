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
import javafx.scene.control.TextArea;

public class ClienteCadastroController implements Initializable {
	
	@FXML
	private TextArea txtNome;
	@FXML
	private TextArea txtTelefone;
	@FXML
	private TextArea txtEmail;

	private ClienteDAO dao;
	// private ObservableList<Cliente> clientes = PrincipalController.Clientes;

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
