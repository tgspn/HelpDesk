package com.helpdesk.Util;

public enum TecnicoType {
	SOFTWARE("Software"),
	HARDWARE("Hardware"),
	REDES("Redes");
	
	private String type;

	TecnicoType(String type){
		this.type=type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
