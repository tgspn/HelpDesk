package com.helpdesk.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.helpdesk.models.Usuario;
import com.helpdesk.repository.UsuarioRepository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDAO implements HelpdeskDAO<Usuario> {

	private UsuarioRepository repository;

	public UsuarioDAO() throws ClassNotFoundException {
		repository = new UsuarioRepository();
		
	}
	@Override
	public void Insert(Usuario inp) throws SQLException {
		repository.Insert(inp);
	}

	@Override
	public void Remove(Usuario inp) throws SQLException {
		repository.Delete(inp.getId());
		
	}

	@Override
	public void Update(Usuario inp) throws SQLException {
		repository.Update(inp);
		
	}

	@Override
	public ObservableList<Usuario> List() throws SQLException {
		ObservableList<Usuario> list=FXCollections.observableArrayList();
		list.addAll(repository.findAll());
		return list;
	}
	public Usuario Login(String user,String senha) throws NoSuchAlgorithmException, SQLException {
		return repository.Login(user, senha);
				
	}

	

}
