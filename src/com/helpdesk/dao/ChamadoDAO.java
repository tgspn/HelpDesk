package com.helpdesk.dao;

import java.util.Comparator;

import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChamadoDAO implements HelpdeskDAO<Chamado> {

	private static ObservableList<Chamado> list;

	public ChamadoDAO() {
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public void Insert(Chamado model) {
		int lastIndex = 0;
		if (list.stream().count() > 0) {
			lastIndex = list.stream().max(Comparator.comparingInt(x -> x.getId())).get().getId();
		}
		model.setId(lastIndex);
		list.add(model);
	}

	@Override
	public void Remove(Chamado model) {
		list.remove(model);
	}

	@Override
	public void Update(Chamado model) {
		Chamado modelOriginal = list.stream().filter(x -> x.getId() == model.getId()).findFirst().get();
		try {
			model.CopyTo(modelOriginal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObservableList<Chamado> List() {
		return list;
	}

}
