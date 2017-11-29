package com.helpdesk.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tecnico {
	private int id;
	private String nome;
	private String categoria;
	private String email;
	private String telefone;
	private int idFuncao;

	public Tecnico(int id, String nome, String categoria, String email, String telefone, int idFuncao) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.email = email;
		this.telefone = telefone;
		this.idFuncao = idFuncao;
	}

	public void CopyTo(Tecnico destino) throws Exception {
		if (destino == null)
			throw new Exception("O destino não pode ser nulo");

		destino.id = this.id;
		destino.idFuncao = this.idFuncao;
		destino.nome = this.nome;
		destino.categoria = this.categoria;
		destino.email = this.email;
		destino.telefone = this.telefone;

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

	public int getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(int idFuncao) {
		this.idFuncao = idFuncao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static Tecnico FromResultSet(ResultSet result) throws SQLException {
		String id="id";
		String categoria="categoria";
		try {result.getString("idTecnico");id="idTecnico";}catch(Exception e){}
		try {result.getString("usertype");id="usertype";}catch(Exception e){}
		
		return new Tecnico(result.getInt(id), result.getString("nome"),result.getString(categoria),result.getString("email"),result.getString("telefone"), result.getInt("idFuncao"));
	}

}
