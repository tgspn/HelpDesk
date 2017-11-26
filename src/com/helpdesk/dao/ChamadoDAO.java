package com.helpdesk.dao;

import java.sql.SQLException;
import java.util.Comparator;

import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.repository.ChamadoRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChamadoDAO implements HelpdeskDAO<Chamado> {

	private ChamadoRepository repository;
	private static ObservableList<Chamado> list;

	public ChamadoDAO() throws ClassNotFoundException {
		repository = new ChamadoRepository();
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public void Insert(Chamado model) throws SQLException {
		// int lastIndex = 0;
		// if (list.stream().count() > 0) {
		// lastIndex = list.stream().max(Comparator.comparingInt(x ->
		// x.getId())).get().getId()+1;
		// }
		// model.setId(lastIndex);
		int result = repository.Insert(model);
		List();
	}

	@Override
	public void Remove(Chamado model) throws SQLException {
		
		repository.Delete(model.getId());
		
		list.remove(model);
	}

	@Override
	public void Update(Chamado model) throws SQLException {
		// Chamado modelOriginal = list.stream().filter(x -> x.getId() ==
		// model.getId()).findFirst().get();
		// try {
		// model.CopyTo(modelOriginal);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		repository.Update(model);
	}

	@Override
	public ObservableList<Chamado> List() throws SQLException {
		list.clear();
		list.addAll(repository.findAll());
		return list;
	}

}
