package com.helpdesk.models;

public class Tecnico {
	private int id;
	private String nome;
	private int idFuncao;
	
	public Tecnico(int id, String nome, int idFuncao) {
		this.id = id;
		this.nome = nome;
		this.idFuncao = idFuncao;
	}
	
	public void CopyTo(Tecnico destino) throws Exception {
		if (destino == null)
			throw new Exception("O destino não pode ser nulo");

		destino.id = this.id;
		destino.idFuncao = this.idFuncao;
		destino.nome = this.nome;
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


	
}
