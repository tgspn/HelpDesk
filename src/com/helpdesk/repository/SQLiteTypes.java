package com.helpdesk.repository;

public enum SQLiteTypes {
	INTEGER("INTEGER"),
	INTERGER_NOT_NULL_PRIMARYKEY_AUTOINCREMENT("INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"),
	STRING("STRING");
	
	private String type;
	private SQLiteTypes(String type) {
		this.type=type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
