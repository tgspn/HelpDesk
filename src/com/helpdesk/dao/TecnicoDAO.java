package com.helpdesk.dao;

import java.util.Comparator;

import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.models.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TecnicoDAO implements HelpdeskDAO<Usuario> {

	private static ObservableList<Usuario> list;

	public TecnicoDAO() {
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public void Insert(Usuario model) {
		int lastIndex = 0;
		if (list.stream().count() > 0) {
			lastIndex = list.stream().max(Comparator.comparingInt(x -> x.getId())).get().getId();
		}
		model.setId(lastIndex);
		list.add(model);
	}

	@Override
	public void Remove(Usuario model) {
		list.remove(model);
	}

	@Override
	public void Update(Usuario model) {
		Usuario modelOriginal = list.stream().filter(x -> x.getId() == model.getId()).findFirst().get();
		try {
			model.CopyTo(modelOriginal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ObservableList<Usuario> List() {
		return list;
	}

}
