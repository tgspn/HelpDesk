package com.helpdesk.dao;

import java.sql.SQLException;

import javafx.collections.ObservableList;

public interface HelpdeskDAO<T> {

	public T Insert(T inp) throws SQLException;

	public void Remove(T inp) throws SQLException;

	public void Update(T inp) throws SQLException;

	public ObservableList<T> List() throws SQLException;

}
