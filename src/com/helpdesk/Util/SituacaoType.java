package com.helpdesk.Util;

public enum SituacaoType {
	ABERTO("Aberto"),
	BLOQUEADO("Bloqueado"),
	ATEDIMENTO("Atendimento"),
	FINALIZADO("Finalizado");
	
	private String type;
	SituacaoType(String type){
		this.type=type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
