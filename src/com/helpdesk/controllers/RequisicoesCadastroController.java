package com.helpdesk.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.helpdesk.dao.ChamadoDAO;
import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class RequisicoesCadastroController implements Initializable {

	@FXML
	private ComboBox<String> cmbCategoria;
	@FXML
	private TextField txtAssunto;
	@FXML
	private TextArea txtDescricao;
	@FXML
	private TextField txtPesquisa;
	@FXML
	private TextField txtNota;
	@FXML
	private RadioButton rdbAberto;
	@FXML
	private RadioButton rdbAtendimento;
	@FXML
	private RadioButton rdbFechado;
	@FXML
	private Button btnNovo;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnVoltar;

	@FXML
	private TableView<Chamado> dgvRequisicoes;
	
	@FXML
	private TableColumn<Chamado, String> colCategoria;
	@FXML
	private TableColumn<Chamado, String> colAssunto;
	@FXML
	private TableColumn<Chamado, String> colSituacao;
	//@FXML
	//private TableColumn<Chamado, String> colNotas;
	private ChamadoDAO dao;
	private ObservableList<String> Categoria = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new ChamadoDAO();

		cmbCategoria.setItems(Categoria);

		Categoria.add("Redes");
		Categoria.add("Software");
		Categoria.add("Hardware");
		
		colCategoria.setCellValueFactory(new PropertyValueFactory<Chamado, String>("categoria"));
		colAssunto.setCellValueFactory(new PropertyValueFactory<Chamado, String>("assunto"));
		colSituacao.setCellValueFactory(new PropertyValueFactory<Chamado, String>("situacao"));
		//colNotas.setCellValueFactory(new PropertyValueFactory<Chamado, String>("categoria"));
		
		dgvRequisicoes.setItems(dao.List());
		
		dgvRequisicoes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		       btnEditar.setDisable(false);
		       btnExcluir.setDisable(false);
		    }else {
		    	 btnEditar.setDisable(true);
			       btnExcluir.setDisable(true);
		    }
		});
		
	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {

		dao.Insert(new Chamado(0, txtDescricao.getText(), cmbCategoria.getSelectionModel().getSelectedItem(),
				txtAssunto.getText(), "Aberto", 1));
		txtDescricao.clear();
		cmbCategoria.setSelectionModel(null);
		txtAssunto.clear();
		
		txtDescricao.setDisable(true);
		cmbCategoria.setDisable(true);
		txtAssunto.setDisable(true);
		txtNota.setDisable(true);
		btnSalvar.setDisable(true);
		
		// ((Parent) (event.getSource())).getScene().getWindow().hide();
	}
	@FXML
	private void handleVoltarAction(final ActionEvent event) {
		((Parent) (event.getSource())).getScene().getWindow().hide();
	}
	@FXML
	private void handleNovoAction(final ActionEvent event) {
		txtDescricao.setDisable(false);
		cmbCategoria.setDisable(false);
		txtAssunto.setDisable(false);
		txtNota.setDisable(false);
		btnSalvar.setDisable(false);
	}
}
