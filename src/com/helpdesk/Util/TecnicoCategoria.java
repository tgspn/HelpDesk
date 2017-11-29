package com.helpdesk.Util;

public enum TecnicoCategoria {
	TECNICO("Tecnico"),
	ADMINISTRADOR("Administrador");
	
	private String type;

	TecnicoCategoria(String type) {
		this.type=type;
	}
	@Override
	public String toString() {
		return type;
	}
}
