package com.helpdesk.controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.helpdesk.models.Chamado;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NovoRequerimentoController implements Initializable {

	@FXML
	private TextArea txtDescricao;
	@FXML
	private ComboBox<String> cmbCategoria;
	@FXML
	private javafx.scene.control.TextField txtAssunto;

	private ObservableList<Chamado> chamados = PrincipalController.chamados;
	private ObservableList<String> Categoria = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbCategoria.setItems(Categoria);
		
		Categoria.add("Redes");
		Categoria.add("Software");
		Categoria.add("Hardware");	
		
	}
	

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
		int lastIndex=0;
		if(chamados.stream().count()>0) {
		lastIndex=chamados.stream().max(Comparator.comparingInt(x->x.getId())).get().getId();
		}
		chamados.add(new Chamado(++lastIndex, txtDescricao.getText(), cmbCategoria.getSelectionModel().getSelectedItem(),txtAssunto.getText() ,"Aberto", 1));

		((Parent) (event.getSource())).getScene().getWindow().hide();
	}

}
