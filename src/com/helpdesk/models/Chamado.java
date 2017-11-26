package com.helpdesk.models;

public class Chamado {
	
	private int id;
	private String descricao;
	private String categoria;
	private String assunto;
	private String situacao;
	private int idCliente;
	private Tecnico tecnico;
	
	
	public Chamado(int id, String descricao, String categoria, String assunto, String situacao, int idCliente,Tecnico tecnico) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.assunto = assunto;
		this.situacao = situacao;
		this.idCliente = idCliente;
		this.tecnico=tecnico;
	}
	
	
	public void CopyTo(Chamado destino) throws Exception {
		if(destino==null)
			throw new Exception("O destino é nulo");
		
		destino.assunto=this.assunto;
		destino.categoria=this.categoria;
		destino.descricao=this.descricao;
		destino.id=this.id;
		destino.idCliente=this.idCliente;
		destino.situacao=this.situacao;
		this.tecnico.CopyTo(destino.tecnico);
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


	public Tecnico getTecnico() {
		return tecnico;
	}


	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
	
}
