package com.helpdesk.dao;

import java.sql.SQLException;
import java.util.List;

import com.helpdesk.Util.SituacaoType;
import com.helpdesk.models.Chamado;
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
	public Chamado Insert(Chamado model) throws SQLException {

		int result = repository.Insert(model);
		List();
		return model;
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
		List<Chamado> select = repository.findAllForSituacao(situacao.toString());
		
		fillList(select);
		
		return list;
	}

	public ObservableList<Chamado> ListBySituacaoAndCategoria(SituacaoType situacao, String categoria)
			throws SQLException {
		if(categoria.isEmpty())
			return ListBySituacao(situacao);
		
		List<Chamado> select = repository.findAllBySituacaoAndCategoria(situacao.toString(),categoria);
		
		fillList(select);
		
		return list;
	}
	@Override
	public ObservableList<Chamado> List() throws SQLException {
		java.util.List<Chamado> select = repository.findAll();
		fillList(select);
		return list;
	}
	public ObservableList<Chamado> ListForTecnico(int id) throws SQLException {
		java.util.List<Chamado> select = repository.findAllForTecnico(id);
		fillList(select);
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
						e.printStackTrace();
					}
				}
			}
			if(!flag)
				list.add(c);
		}
		
		for (Chamado c : list) {
			boolean flag=false;
			for (Chamado ch : select ) {
				if (c.getId() == ch.getId()) {
					try {
						c.CopyTo(ch);
						flag=true;
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(!flag)
				list.remove(c);
		}
		
	}

	

	

}
