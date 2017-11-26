package com.helpdesk.repository;

public enum SQLLiteTypes {
	INTEGER("INTEGER"),
	INTERGER_NOT_NULL_PRIMARYKEY_AUTOINCREMENT("INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"),
	STRING("STRING");
	
	private String type;
	private SQLLiteTypes(String type) {
		this.type=type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
