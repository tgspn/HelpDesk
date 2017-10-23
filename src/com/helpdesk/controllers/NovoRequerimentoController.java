package com.helpdesk.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NovoRequerimentoController implements Initializable {

	@FXML
	private TextArea txtDescricao;
	private ObservableList<String> names = PrincipalController.names;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
		names.add(txtDescricao.getText());

		((Parent) (event.getSource())).getScene().getWindow().hide();
	}

}
