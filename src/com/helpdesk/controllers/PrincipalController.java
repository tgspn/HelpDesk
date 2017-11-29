package com.helpdesk.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.helpdesk.Util.Configuracao;
import com.helpdesk.Util.SituacaoType;
import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.Util.TecnicoType;
import com.helpdesk.Util.Util;
import com.helpdesk.controllers.PrincipalTecnicoController.UpdateTimer;
import com.helpdesk.dao.ChamadoDAO;
import com.helpdesk.dao.ClienteDAO;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.repository.ConnectionSingleton;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class PrincipalController implements Initializable {

	@FXML
	private MenuBar menuBar;
	@FXML
	private Button btnClientes;
	@FXML
	private Button btnRequisicoes;
	@FXML
	private Button btnTecnicos;

	private ObservableList<Chamado> chamados;
	private ObservableList<Cliente> Clientes;// = FXCollections.observableArrayList();
	@FXML
	private ListView<Chamado> lvRequerimentos;
	private Timer timer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			com.helpdesk.dao.ClienteDAO c = new ClienteDAO();

			Clientes = c.List();

			com.helpdesk.dao.ChamadoDAO cha;

			cha = new ChamadoDAO();

			chamados = cha.List();

			btnClientes.setVisible(false);
			btnTecnicos.setVisible(false);
			if(Configuracao.getCurrent().getTecnico().getCategoria().isEmpty())
				btnTecnicos.setVisible(true);
			lvRequerimentos.setItems(chamados);
			lvRequerimentos.setCellFactory(new Callback<ListView<Chamado>, ListCell<Chamado>>() {

				@Override
				public ListCell<Chamado> call(ListView<Chamado> p) {

					ListCell<Chamado> cell = new ListCell<Chamado>() {

						@Override
						protected void updateItem(Chamado t, boolean bln) {
							super.updateItem(t, bln);
							if (t != null) {
								setText("[" + t.getId() + "] " + t.getAssunto() + " - " + t.getCategoria()+" - "+t.getSituacao());
							}
						}

					};

					return cell;
				}
			});
			
			timer = new Timer();
			timer.schedule(new UpdateTimer(cha), 1000, 1000);

		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
	}

	@FXML
	private void handleRequisicoesAction(final ActionEvent event) {
		Util.OpenScene("CadastroRequisicoes.fxml").setOnHiding(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				lvRequerimentos.refresh();

			}
		});
	}

	@FXML
	private void handleClienteAction(final ActionEvent evet) {
		Util.OpenScene("CadastroCliente.fxml");
	}

	@FXML
	private void handleTecnicosAction(final ActionEvent evt) {
		Util.OpenScene("CadastroTecnicos.fxml");

	}

	@FXML
	private void handleCloseAction(final ActionEvent event) {
		Close();
	}

	private void Close() {
		try {
			ConnectionSingleton.getInstance().Close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}
	
	class UpdateTimer extends TimerTask {

		private ChamadoDAO dao;

		public UpdateTimer(ChamadoDAO dao) {
			this.dao = dao;
		}

		public void run() {
			try {
				dao.List();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
