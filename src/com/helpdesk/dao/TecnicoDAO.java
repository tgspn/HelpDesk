package com.helpdesk.dao;

import java.util.Comparator;

import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.models.Tecnico;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TecnicoDAO implements HelpdeskDAO<Tecnico> {

	private static ObservableList<Tecnico> list;

	public TecnicoDAO() {
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public void Insert(Tecnico model) {
		int lastIndex = 0;
		if (list.stream().count() > 0) {
			lastIndex = list.stream().max(Comparator.comparingInt(x -> x.getId())).get().getId();
		}
		model.setId(lastIndex);
		list.add(model);
	}

	@Override
	public void Remove(Tecnico model) {
		list.remove(model);
	}

	@Override
	public void Update(Tecnico model) {
		Tecnico modelOriginal = list.stream().filter(x -> x.getId() == model.getId()).findFirst().get();
		try {
			model.CopyTo(modelOriginal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObservableList<Tecnico> List() {
		return list;
	}

}
