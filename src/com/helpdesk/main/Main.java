package com.helpdesk.main;

import java.io.File;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.Util.Util;
import com.helpdesk.models.Tecnico;
import com.helpdesk.models.Usuario;
import com.helpdesk.repository.ChamadoRepository;
import com.helpdesk.repository.TecnicoRepository;
import com.helpdesk.repository.UsuarioRepository;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(Main.class, args);

	}

	@Override
	public void start(final Stage stage) throws Exception {
		CheckDB();
//		FXMLLoader f = new FXMLLoader();
//		final Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/Login.fxml")));
//		stage.setScene(new Scene(fxmlRoot));
//		stage.show();
		
		Util.OpenScene("Login.fxml");

	}

	private void CheckDB() {
		File f = new File("sample.db");
		if (!f.exists()) {
			ChamadoRepository.Initialize();
			UsuarioRepository.Initialize();
			TecnicoRepository.Initialize();

			CreateUsuarioDefault();

		}

	}

	private void CreateUsuarioDefault() {

		CreateUsuarioDefault("Administrador", TecnicoCategoria.ADMINISTRADOR, "admin", "123");
		CreateUsuarioDefault("Tecnico", TecnicoCategoria.TECNICO, "tec", "123");
	}

	private void CreateUsuarioDefault(String nome, TecnicoCategoria type, String usuario, String senha) {
		try {
			Tecnico tec = new Tecnico(0, nome,"","","",1);
			Usuario use = new Usuario(0, type.toString(), usuario, senha, tec);

			TecnicoRepository tecRep = new TecnicoRepository();
			tecRep.Insert(tec);

			UsuarioRepository useRep = new UsuarioRepository();
			useRep.Insert(use);

		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
