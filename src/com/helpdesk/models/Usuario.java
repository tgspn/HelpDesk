package com.helpdesk.models;

public class Usuario {
	private int id;
	private String nome;
	private String tipoUsuario;
	private String usuario;
	private String senha;
	private int idFuncao;

	public Usuario(int id, String nome, String tipoUsuario, String usuario, String senha, int idFuncao) {
		this.id = id;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.usuario = usuario;
		this.senha = senha;
		this.idFuncao = idFuncao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(int idFuncao) {
		this.idFuncao = idFuncao;
	}

	public void CopyTo(Usuario destino) throws Exception {
		if (destino == null)
			throw new Exception("O destino não pode ser nulo");

		destino.id = this.id;
		destino.idFuncao = this.idFuncao;
		destino.nome = this.nome;
		destino.senha = this.senha;
		destino.tipoUsuario = this.tipoUsuario;
		destino.usuario = this.usuario;
	}
}
