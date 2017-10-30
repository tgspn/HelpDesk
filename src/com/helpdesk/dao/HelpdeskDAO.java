package com.helpdesk.dao;

import javafx.collections.ObservableList;

public interface HelpdeskDAO<T> {

	public void Insert(T inp);
	public void Remove(T inp);
	public void Update(T inp);
	public ObservableList<T> List();
	
}
