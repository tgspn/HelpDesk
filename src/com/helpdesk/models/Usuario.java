package com.helpdesk.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.helpdesk.Util.Util;

public class Usuario {
	private int id;
	private String tipoUsuario;
	private String usuario;
	private String senha;
	private Tecnico tecnico;

	public Usuario(int id, String tipoUsuario, String usuario, String senha, Tecnico tecnico) throws NoSuchAlgorithmException {
		this.id = id;
		this.tipoUsuario = tipoUsuario;
		this.usuario = usuario;
		setSenha( senha);
		this.tecnico = tecnico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws NoSuchAlgorithmException {		

		this.senha =Util.MD5( senha);
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	

}
