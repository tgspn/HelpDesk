package com.helpdesk.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.helpdesk.models.Chamado;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

public class ClienteCadastroController implements Initializable{
	@FXML
	private TextArea txtDescricao;
	@FXML
	private TextArea txtNome;
	@FXML
	private TextArea txtTelefone;
	@FXML
	private TextArea txtEmail;
	
	private ObservableList<Chamado> names = PrincipalController.chamados;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
		//names.add(txtDescricao.getText());

		((Parent) (event.getSource())).getScene().getWindow().hide();
	}
}
