package com.helpdesk.Util;

public enum UserType {
	TECNICO("Tecnico"),
	ADMINISTRADOR("Administrador");
	
	private String type;

	UserType(String type) {
		this.type=type;
	}
	@Override
	public String toString() {
		return type;
	}
}
