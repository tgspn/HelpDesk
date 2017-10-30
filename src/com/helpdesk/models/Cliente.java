package com.helpdesk.models;

public class Cliente {
	private int id;
	private String nome;
	private String telefone;
	private String email;

	public Cliente(int id, String nome, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void CopyTo(Cliente destino) throws Exception {

		if (destino == null)
			throw new Exception("O destino não pode ser nulo");

		destino.id=this.id;
		destino.email=this.email;
		destino.nome=this.nome;
		destino.telefone=this.telefone;				
	}
	
	public Cliente Clone()
	{
		Cliente clone=new Cliente(this.id, this.nome, this.telefone, this.email);
		return clone;
				
	}
}
