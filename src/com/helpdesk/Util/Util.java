package com.helpdesk.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.helpdesk.controllers.CadastroUsuarioController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Util {
	public static String MD5(String md5) throws NoSuchAlgorithmException {

		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		byte[] array = md.digest(md5.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();

	}
	public static Stage OpenScene(String fileName) {
		FXMLLoader f = new FXMLLoader();
		Stage stage = new Stage();
		Parent fxmlRoot;
		try {
			fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/" + fileName)));
			stage.setScene(new Scene(fxmlRoot));
			
			stage.show();
			return stage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public static Stage OpenScene(String fileName, CadastroUsuarioController cad) {
		FXMLLoader f = new FXMLLoader();
		Stage stage = new Stage();
		Parent fxmlRoot;
		try {
			
					
			fxmlRoot = (Parent) f.load(new FileInputStream(new File("src/com/helpdesk/views/" + fileName)));
			f.setController(cad);
			stage.setScene(new Scene(fxmlRoot));
			
			stage.show();
			return stage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
