package com.helpdesk.models;

public class Chamado {
	
	private int id;
	private String descricao;
	private String categoria;
	private String assunto;
	private String situacao;
	private int idCliente;
	public Chamado(int id, String descricao, String categoria, String assunto, String situacao, int idCliente) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.assunto = assunto;
		this.situacao = situacao;
		this.idCliente = idCliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
}
