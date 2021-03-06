package com.helpdesk.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.helpdesk.Util.Configuracao;
import com.helpdesk.Util.MessageBox;
import com.helpdesk.Util.SituacaoType;
import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.dao.ChamadoDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Tecnico;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class PrincipalTecnicoController implements Initializable {

	@FXML
	private TableView<Chamado> lvRequerimentos;
	@FXML
	private TableView<Chamado> lvMyRequerimentos;

	@FXML
	private TableColumn<Chamado, String> colNro;
	@FXML
	private TableColumn<Chamado, String> colCategoria;
	@FXML
	private TableColumn<Chamado, String> colAssunto;
	@FXML
	private TableColumn<Chamado, String> colDescricao;

	@FXML
	private TableColumn<Chamado, String> colMyNro;
	@FXML
	private TableColumn<Chamado, String> colMyCategoria;
	@FXML
	private TableColumn<Chamado, String> colMyAssunto;
	@FXML
	private TableColumn<Chamado, String> colMyDescricao;

	@FXML
	private Button btnFinalizar;

	@FXML
	private Button btnBloquear;

	@FXML
	private Button btnDesbloquear;

	@FXML
	private Button btnDetalhes;

	private Timer timer;
	private Timer timerMy;
	private Tecnico tecnico = Configuracao.getCurrent().getTecnico();
	private com.helpdesk.dao.ChamadoDAO dao;
	private com.helpdesk.dao.ChamadoDAO daoMy;
	@FXML
	private AnchorPane panel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			btnBloquear.setDisable(true);
			btnDesbloquear.setDisable(true);
			btnDetalhes.setDisable(true);
			btnFinalizar.setDisable(true);

			dao = new ChamadoDAO();
			daoMy = new ChamadoDAO();

			colNro.setCellValueFactory(new PropertyValueFactory<>("id"));
			colCategoria.setCellValueFactory(new PropertyValueFactory<Chamado, String>("categoria"));
			colAssunto.setCellValueFactory(new PropertyValueFactory<Chamado, String>("assunto"));
			colDescricao.setCellValueFactory(new PropertyValueFactory<Chamado, String>("situacao"));

			colMyNro.setCellValueFactory(new PropertyValueFactory<>("id"));
			colMyCategoria.setCellValueFactory(new PropertyValueFactory<Chamado, String>("categoria"));
			colMyAssunto.setCellValueFactory(new PropertyValueFactory<Chamado, String>("assunto"));
			colMyDescricao.setCellValueFactory(new PropertyValueFactory<Chamado, String>("situacao"));

			lvRequerimentos.setItems(dao.ListBySituacaoAndCategoria(SituacaoType.ABERTO,tecnico.getCategoria()));
			lvMyRequerimentos.setItems(daoMy.ListForTecnico(tecnico.getId()));

			lvRequerimentos.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						if (newSelection != null) {
							btnBloquear.setDisable(false);

						} else {
							btnBloquear.setDisable(true);
						}
					});

			lvMyRequerimentos.getSelectionModel().selectedItemProperty()
					.addListener((obs, oldSelection, newSelection) -> {
						if (newSelection != null) {
							btnDetalhes.setDisable(false);
							btnDesbloquear.setDisable(false);
							btnFinalizar.setDisable(false);

						} else {
							btnBloquear.setDisable(true);
							btnDesbloquear.setDisable(true);
							btnFinalizar.setDisable(true);
						}
					});

			timer = new Timer();
			timer.schedule(new UpdateTimer(dao), 1000, 1000);

			timerMy = new Timer();
			timerMy.schedule(new UpdateMyTimer(daoMy), 1000, 3000);

			Timer t = new Timer();
			t.schedule(new TimerTask() {

				@Override
				public void run() {
					panel.getScene().getWindow().setOnCloseRequest((x) -> {
						timer.cancel();
						timerMy.cancel();
						timer.purge();
						timerMy.purge();
						t.cancel();
						t.purge();
						System.out.println("Saindo");
					});

				}
			}, 1000);

		} catch (ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		} catch (SQLException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}
	}

	private void setTimer() {
		timer = new Timer();
		timer.schedule(new UpdateTimer(dao), 1000, 1000);
	}

	private void pararTimer() {
		timer.cancel();
		timer.purge();
	}

	private void setMyTimer() {
		timerMy = new Timer();
		timerMy.schedule(new UpdateMyTimer(daoMy), 1000, 1000);
	}

	private void pararMyTimer() {
		timerMy.cancel();
		timerMy.purge();
	}

	@FXML
	private void bloquearHandle(final ActionEvent event) {

		pararMyTimer();
		pararTimer();

		Chamado model = lvRequerimentos.getSelectionModel().selectedItemProperty().get();

		model.setSituacao(SituacaoType.BLOQUEADO.toString());
		model.setTecnico(tecnico);

		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.Update(model);
		} catch (SQLException | ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		} finally {
			updateTableView();
			setMyTimer();
			setTimer();
		}
	}

	private void updateTableView() {
		lvMyRequerimentos.refresh();
		lvRequerimentos.refresh();
	}

	@FXML
	private void desbloquearHandle(final ActionEvent event) {
		pararTimer();
		pararMyTimer();
		Chamado m = lvMyRequerimentos.getSelectionModel().selectedItemProperty().get();

		m.setSituacao(SituacaoType.ABERTO.toString());
		m.setTecnico(null);

		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.Update(m);
		} catch (SQLException | ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		} finally {
			updateTableView();
			setMyTimer();
			setTimer();
		}
	}

	@FXML
	private void finalizarHandle(final ActionEvent event) {
		pararMyTimer();
		Chamado model = lvMyRequerimentos.getSelectionModel().selectedItemProperty().get();

		model.setSituacao(SituacaoType.FINALIZADO.toString());
		model.setTecnico(null);

		try {
			ChamadoDAO dao = new ChamadoDAO();
			dao.Update(model);
		} catch (SQLException | ClassNotFoundException e) {
			MessageBox.Show(e.getMessage(), "Erro", AlertType.ERROR);
		}

		updateTableView();
		setMyTimer();
	}

	class UpdateTimer extends TimerTask {

		private ChamadoDAO dao;

		public UpdateTimer(ChamadoDAO dao) {
			this.dao = dao;
		}

		public void run() {
			try {
				dao.ListBySituacaoAndCategoria(SituacaoType.ABERTO,tecnico.getCategoria());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	class UpdateMyTimer extends TimerTask {

		private ChamadoDAO dao;

		public UpdateMyTimer(ChamadoDAO dao) {
			this.dao = dao;
		}

		public void run() {
			try {
				dao.ListForTecnico(tecnico.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
