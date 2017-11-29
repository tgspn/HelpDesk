package com.helpdesk.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.helpdesk.Util.Configuracao;
import com.helpdesk.Util.MessageBox;
import com.helpdesk.Util.Util;
import com.helpdesk.dao.ChamadoDAO;
import com.helpdesk.models.Chamado;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private Label lblAberto;
	@FXML
	private Label lblBloqueado;
	@FXML
	private Label lblAtendimento;
	@FXML
	private Label lblFinalizado;

	@FXML
	private TableColumn<Chamado, String> colCategoria;
	@FXML
	private TableColumn<Chamado, String> colAssunto;
	@FXML
	private TableColumn<Chamado, String> colSituacao;
	// @FXML
	// private TableColumn<Chamado, String> colNotas;
	private ChamadoDAO dao;
	private Chamado model;
	private ObservableList<String> Categoria = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			dao = new ChamadoDAO();

			cmbCategoria.setItems(Categoria);

			Categoria.add("Redes");
			Categoria.add("Software");
			Categoria.add("Hardware");

			colCategoria.setCellValueFactory(new PropertyValueFactory<Chamado, String>("categoria"));
			colAssunto.setCellValueFactory(new PropertyValueFactory<Chamado, String>("assunto"));
			colSituacao.setCellValueFactory(new PropertyValueFactory<Chamado, String>("situacao"));

			dgvRequisicoes.setItems(dao.List());

			dgvRequisicoes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
				if (newSelection != null) {
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);

					Chamado model = newSelection;
					txtDescricao.setText(model.getDescricao());
					txtAssunto.setText(model.getAssunto());
					cmbCategoria.setValue(model.getCategoria());
					setSituacao(model.getSituacao());

				} else {
					btnEditar.setDisable(true);
					btnExcluir.setDisable(true);
					setInitial();
				}
			});

			setInitial();
		} catch (ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		} catch (Exception e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}

	}

	@FXML
	private void handleConfirmarAction(final ActionEvent event) {
		try {
			if (model == null) {
				String situacao = "Aberto";

				dao.Insert(new Chamado(0, txtDescricao.getText(), cmbCategoria.getSelectionModel().getSelectedItem(),
						txtAssunto.getText(), situacao, 1,Configuracao.getCurrent().getTecnico(),txtNota.getText()));

			} else {

				model.setDescricao(txtDescricao.getText());
				model.setCategoria(cmbCategoria.getValue());
				model.setAssunto(txtAssunto.getText());

				dao.Update(model);

				dgvRequisicoes.refresh();

			}
			setInitial();

		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
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

		txtDescricao.clear();
		cmbCategoria.setValue(null);
		txtAssunto.clear();
	}

	@FXML
	private void handleEditAction(final ActionEvent event) {

		model = dgvRequisicoes.getSelectionModel().selectedItemProperty().get();
		txtDescricao.setText(model.getDescricao());
		txtAssunto.setText(model.getAssunto());
		cmbCategoria.setValue(model.getCategoria());
		setSituacao(model.getSituacao());

		txtDescricao.setDisable(false);
		cmbCategoria.setDisable(false);
		txtAssunto.setDisable(false);
		txtNota.setDisable(false);
		btnSalvar.setDisable(false);

	}

	@FXML
	private void handleExcluirAction(final ActionEvent event) {
		Chamado model = dgvRequisicoes.getSelectionModel().selectedItemProperty().get();
		try {
			dao.Remove(model);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
	}

	private void setSituacao(String situacao) {
		lblAberto.setVisible(false);
		lblAtendimento.setVisible(false);
		lblBloqueado.setVisible(false);
		lblFinalizado.setVisible(false);

		switch (situacao) {
		case "Aberto":
			lblAberto.setVisible(true);
			break;
		case "Bloqueado":
			lblBloqueado.setVisible(true);
			break;
		case "Em Atendimento":
			lblAtendimento.setVisible(true);
			break;
		case "Finalizado":
			lblFinalizado.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void setInitial() {
		txtDescricao.clear();
		cmbCategoria.setValue(null);
		txtAssunto.clear();

		txtDescricao.setDisable(true);
		cmbCategoria.setDisable(true);
		txtAssunto.setDisable(true);
		txtNota.setDisable(true);
		btnSalvar.setDisable(true);

		setSituacao("");
	}
}
