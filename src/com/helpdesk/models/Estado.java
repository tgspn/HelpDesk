package com.helpdesk.models;

public class Estado {
	private int id;
	private int idChamado;
	private String ocorrencia;
	private String data;
	private String hora;
	private int IdUsuario;
	
	public Estado(int id, int idChamado, String ocorrencia, String data, String hora, int idUsuario) {
		super();
		this.id = id;
		this.idChamado = idChamado;
		this.ocorrencia = ocorrencia;
		this.data = data;
		this.hora = hora;
		IdUsuario = idUsuario;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdChamado() {
		return idChamado;
	}
	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	
	
}
