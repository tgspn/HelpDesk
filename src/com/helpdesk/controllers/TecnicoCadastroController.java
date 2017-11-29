package com.helpdesk.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.helpdesk.Util.MessageBox;
import com.helpdesk.Util.TecnicoType;
import com.helpdesk.Util.Util;
import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.dao.TecnicoDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.models.Tecnico;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;;

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
	private TableView<Tecnico> dgvCliente;

	@FXML
	private TableColumn<Tecnico, String> colNome;
	@FXML
	private TableColumn<Tecnico, String> colEmail;
	@FXML
	private TableColumn<Tecnico, String> colTelefone;
	@FXML
	private TableColumn<Tecnico, String> colCategoria;

	@FXML
	private RadioButton rbSoftware;
	@FXML
	private RadioButton rbHardware;
	@FXML
	private RadioButton rbRedes;

	@FXML
	private Label lblNewUser;

	private TecnicoDAO dao;
	private Tecnico model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			dao = new TecnicoDAO();

			colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
			colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

			dgvCliente.setItems(dao.List());

		} catch (ClassNotFoundException | SQLException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}
	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
		try {

			Salvar();

			setInitial();

		} catch (SQLException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}

	}

	private void Salvar() throws SQLException {

		TecnicoType category = TecnicoType.HARDWARE;
		if (rbSoftware.isSelected())
			category = TecnicoType.SOFTWARE;
		else if (rbRedes.isSelected())
			category = TecnicoType.REDES;

		if (model == null) {
			model = dao.Insert(new Tecnico(0, txtNome.getText(), category.toString(), txtEmail.getText(),
					txtTelefone.getText(), 0));
		} else {
			model.setNome(txtNome.getText());
			model.setEmail(txtEmail.getText());
			model.setTelefone(txtTelefone.getText());
			model.setCategoria(category.toString());
		}
	}
	@FXML
	private void newUserHandle(final MouseEvent  event) {
		try {
			Salvar();

			CadastroUsuarioController cad=new CadastroUsuarioController();
			
			FXMLLoader f = new FXMLLoader();
			Stage stage = new Stage();
			Parent fxmlRoot;
			try {
				
				
				fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/CadastroUsuario.fxml")));
				cad=f.getController();
				cad.setTecnico(model);
				
				stage.setScene(new Scene(fxmlRoot));
				
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setInitial() {
		txtNome.clear();
		txtBusca.clear();
		txtEmail.clear();
		txtTelefone.clear();
	}

}
