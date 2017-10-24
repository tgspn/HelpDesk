package com.helpdesk.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.helpdesk.models.Chamado;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListViewBuilder;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PrincipalController implements Initializable {

	@FXML
	private MenuBar menuBar;
	public static final ObservableList<Chamado> chamados = FXCollections.observableArrayList();
	@FXML
	private ListView<Chamado> lvRequerimentos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuBar.setFocusTraversable(true);
		lvRequerimentos.setItems(chamados);
		lvRequerimentos.setCellFactory(new Callback<ListView<Chamado>, ListCell<Chamado>>(){

            @Override
            public ListCell<Chamado> call(ListView<Chamado> p) {
                
                ListCell<Chamado> cell = new ListCell<Chamado>(){

                    @Override
                    protected void updateItem(Chamado t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText("["+t.getId()+"] "+t.getAssunto()+ " - " + t.getCategoria());
                        }
                    }

                };
                
                return cell;
            }
        });
	}

	@FXML
	private void handleNovoAction(final ActionEvent event) {
		FXMLLoader f = new FXMLLoader();
		Stage stage = new Stage();
		Parent fxmlRoot;
		try {
			fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/NovoRequerimento.fxml")));
			stage.setScene(new Scene(fxmlRoot));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void handleCloseAction(final ActionEvent event) {
		Close();
	}

	private void Close() {
		System.exit(0);

	}

	private void provideAboutFunctionality() {
		System.out.println("You clicked on About!");
	}

}
