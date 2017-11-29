package com.helpdesk.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.helpdesk.Util.Configuracao;
import com.helpdesk.Util.MessageBox;
import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.Util.Util;
import com.helpdesk.dao.UsuarioDAO;
import com.helpdesk.models.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {

	@FXML
	private TextField txtUser;
	@FXML
	private TextField txtPass;
	private UsuarioDAO dao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			this.dao = new UsuarioDAO();
			
			
		} catch (ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}

	}

	@FXML
	private void Login(final ActionEvent event) {

		Usuario user;
		try {
			user = this.dao.Login(txtUser.getText(), txtPass.getText());
			if (user != null) {
				Configuracao.getCurrent().setTecnico(user.getTecnico());
				
				if (user.getTipoUsuario().equals( TecnicoCategoria.ADMINISTRADOR.toString()))
					Util.OpenScene("Principal.fxml");
				else if (user.getTipoUsuario().equals( TecnicoCategoria.TECNICO.toString()))
					Util.OpenScene("PrincipalTecnicos.fxml");
				else {
					MessageBox.Show("Não foi encontrado um tela para o seu perfil", "Erro", AlertType.ERROR);
				}
				((Parent) (event.getSource())).getScene().getWindow().hide();

			} else {
				MessageBox.Show("Usuário ou senha incorreto", "Erro", AlertType.ERROR);
			}

		} catch (NoSuchAlgorithmException | SQLException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}

	}
	
	@FXML
	private void close(final ActionEvent event) {
		((Parent) (event.getSource())).getScene().getWindow().hide();
	}

}
