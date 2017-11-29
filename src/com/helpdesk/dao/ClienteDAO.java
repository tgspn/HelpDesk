package com.helpdesk.dao;

import java.util.Comparator;

import com.helpdesk.models.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDAO implements HelpdeskDAO<Cliente> {

	private static ObservableList<Cliente> list;

	public ClienteDAO() {
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public Cliente Insert(Cliente client) {
		int lastIndex = 0;
		if (list.stream().count() > 0) {
			lastIndex = list.stream().max(Comparator.comparingInt(x -> x.getId())).get().getId();
		}
		client.setId(lastIndex);
		list.add(client);
		return client;
	}

	@Override
	public void Remove(Cliente client) {
		list.remove(client);
	}

	@Override
	public void Update(Cliente client) {
		Cliente clientOriginal = list.stream().filter(x -> x.getId() == client.getId()).findFirst().get();
		try {
			client.CopyTo(clientOriginal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObservableList<Cliente> List() {
		return list;
	}

}
