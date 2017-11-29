package com.helpdesk.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.helpdesk.Util.MessageBox;
import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.dao.UsuarioDAO;
import com.helpdesk.models.Tecnico;
import com.helpdesk.models.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroUsuarioController implements Initializable {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private PasswordField txtConfSenha;

	@FXML
	private ComboBox<TecnicoCategoria> cmbUserType;

	private UsuarioDAO dao;
	private Usuario model;
	private Tecnico tecnico;

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
		try {
			model = dao.findByTecnico(tecnico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			dao = new UsuarioDAO();

			

			ObservableList<TecnicoCategoria> userTypes = FXCollections.observableArrayList();
			userTypes.add(TecnicoCategoria.ADMINISTRADOR);
			userTypes.add(TecnicoCategoria.TECNICO);
			cmbUserType.setItems(userTypes);

		} catch (Exception e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}
	}
	@FXML
	private void SalvarHandle(final ActionEvent event) {
		try {

			if (!txtSenha.getText().equals(txtConfSenha.getText()))
				throw new Exception("As senhas não são iguais");

			if (model == null) {

				model = new Usuario(0, cmbUserType.getSelectionModel().getSelectedItem().toString(), txtUser.getText(),
						txtSenha.getText(), tecnico);
				dao.Insert(model);

			} else {
				model.setSenha(txtSenha.getText());
				model.setTipoUsuario(txtSenha.getText());
				model.setTipoUsuario(cmbUserType.getSelectionModel().getSelectedItem().toString());
			}
		} catch (Exception e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}
	}

}
