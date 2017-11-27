package com.helpdesk.dao;

import java.sql.SQLException;
import java.util.Comparator;

import com.helpdesk.Util.SituacaoType;
import com.helpdesk.models.Chamado;
import com.helpdesk.models.Cliente;
import com.helpdesk.repository.ChamadoRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChamadoDAO implements HelpdeskDAO<Chamado> {

	private ChamadoRepository repository;
	private ObservableList<Chamado> list;

	public ChamadoDAO() throws ClassNotFoundException {
		repository = new ChamadoRepository();
		if (list == null)
			list = FXCollections.observableArrayList();
	}

	@Override
	public void Insert(Chamado model) throws SQLException {

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

		repository.Update(model);
	}
	
	public ObservableList<Chamado>ListBySituacao(SituacaoType situacao) throws SQLException{
		java.util.List<Chamado> select = repository.findAllForSituacao(situacao.toString());
		
		fillList(select);
		
		return list;
	}
	@Override
	public ObservableList<Chamado> List() throws SQLException {
//		list.clear();
		java.util.List<Chamado> select = repository.findAll();
		fillList(select);
//		list.addAll(repository.findAllForTecnico(id));
//		list.addAll(repository.findAll());
		return list;
	}
	private void fillList(java.util.List<Chamado> select) {
		for (Chamado c : select) {
			boolean flag=false;
			for (Chamado ch : list ) {
				if (c.getId() == ch.getId()) {
					try {
						c.CopyTo(ch);
						flag=true;
						break;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(!flag)
				list.add(c);
		}
	}

	
	public ObservableList<Chamado> ListForTecnico(int id) throws SQLException {
		// list.clear();
		java.util.List<Chamado> select = repository.findAllForTecnico(id);
		for (Chamado c : select) {
			boolean flag=false;
			for (Chamado ch : list ) {
				if (c.getId() == ch.getId()) {
					try {
						c.CopyTo(ch);
						flag=true;
						break;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(!flag)
				list.add(c);
		}
//		list.addAll(repository.findAllForTecnico(id));
		return list;
	}

}
