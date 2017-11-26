package com.helpdesk.repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class ConnectionSingleton {
	
	private static ConnectionSingleton instance;
	private Connection connection;

	private ConnectionSingleton() throws ClassNotFoundException {


		Class.forName("org.sqlite.JDBC");

		
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");			
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
		} 
	}
	
	public Connection getConnection() {
		
		return connection;
		
	}
	public void Close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) { // Use SQLException class instead.
			System.err.println(e);
		}
	}
	public static ConnectionSingleton getInstance() throws ClassNotFoundException {
		if(instance==null)
			instance=new ConnectionSingleton();
		
		return instance;
	}
}
