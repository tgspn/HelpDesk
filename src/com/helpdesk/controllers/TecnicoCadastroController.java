package com.helpdesk.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.helpdesk.Util.MessageBox;
import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.dao.TecnicoDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.models.Tecnico;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;;

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

	private TecnicoDAO dao;
	private Tecnico model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			dao = new TecnicoDAO();
		} catch (ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}
	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
//		if (model == null) {
//
//			dao.Insert(getModel());
//		} else {
//			model.setNome(txtNome.getText());
//			
//
//		}
		setInitial();
	}

//	private Tecnico getModel() {
//
//		return new Tecnico(0, txtNome.getText(), "", "", "", 0);
//	}
	private void setInitial() {
		txtNome.clear();
		txtBusca.clear();
		txtEmail.clear();
		txtTelefone.clear();
	}

}
