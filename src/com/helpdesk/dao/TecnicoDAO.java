package com.helpdesk.dao;

import java.sql.SQLException;
import java.util.Comparator;

import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.models.Tecnico;
import com.helpdesk.repository.TecnicoRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TecnicoDAO implements HelpdeskDAO<Tecnico> {

	private ObservableList<Tecnico> list;
	private TecnicoRepository repository;

	public TecnicoDAO() throws ClassNotFoundException {
		this.repository = new TecnicoRepository();
		list = FXCollections.observableArrayList();
	}

	@Override
	public Tecnico Insert(Tecnico model) throws SQLException {
		repository.Insert(model);
		list.add(model);
		return model;
	}

	@Override
	public void Remove(Tecnico model) throws SQLException {
		repository.Delete(model.getId());
		list.remove(model);
	}

	@Override
	public void Update(Tecnico model) throws SQLException {
		repository.Update(model);

	}

	@Override
	public ObservableList<Tecnico> List() throws SQLException {
		list.addAll(repository.findAll());
		return list;
	}

}
