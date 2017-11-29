package com.helpdesk.repository;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.helpdesk.Util.TecnicoCategoria;
import com.helpdesk.Util.Util;
import com.helpdesk.models.Tecnico;
import com.helpdesk.models.Usuario;

public class UsuarioRepository extends repositoryBase<Usuario> {

	public UsuarioRepository() throws ClassNotFoundException {
		super();
	}

	@Override
	protected String[] getFieldsInsert() {
		List<String> list = new ArrayList<>();
		list.add("tipoUsuario");
		list.add("usuario");
		list.add("senha");
		list.add("idTecnico");
		return list.toArray(new String[0]);
	}

	@Override
	protected String getPrimaryKeyField() {
		return "id";
	}

	@Override
	protected int getPrimaryKeyValue(Usuario obj) {
		return obj.getId();
	}

	@Override
	protected void setPrimaryKeyValue(Usuario obj, int value) {
		obj.setId(value);

	}

	@Override
	protected Object[] getValueInsert(Usuario obj) {

		List<Object> list = new ArrayList<>();
		list.add(obj.getTipoUsuario());
		list.add(obj.getUsuario());
		list.add(obj.getSenha());
		list.add(obj.getTecnico().getId());
		return list.toArray();
	}

	@Override
	protected Map<String, Object> getUpdateValues(Usuario obj) {
		Map<String, Object> map = new HashMap();

		map.put("senha", obj.getSenha());
		map.put("idTecnico", obj.getTecnico().getId());
		map.put("tipoUsuario", obj.getTipoUsuario());
		map.put("usuario", obj.getUsuario());
		return map;
	}

	@Override
	protected Usuario fillObject(ResultSet result) throws SQLException {
		Tecnico tecnico = null;

		try {
			if (result.getString("idTecnico") != null)
				tecnico = Tecnico.FromResultSet(result);

			return new Usuario(result.getInt("id"), result.getString("tipoUsuario"), result.getString("usuario"),
					result.getString("senha"), tecnico);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Map<String, SQLiteTypes> defineFields() {
		Map<String, SQLiteTypes> map = new HashMap<>();
		map.put("id", SQLiteTypes.INTERGER_NOT_NULL_PRIMARYKEY_AUTOINCREMENT);
		map.put("tipoUsuario", SQLiteTypes.STRING);
		map.put("usuario", SQLiteTypes.STRING);
		map.put("senha", SQLiteTypes.STRING);
		map.put("idTecnico", SQLiteTypes.INTEGER);
		return map;
	}

	@Override
	protected String defineTableName() {
		return "usuario";
	}

	@Override
	protected String defineDefaultJoin() {
		return "LEFT JOIN tecnico on tecnico.id=idTecnico";
	}

	@Override
	protected String defineDefaultParams() {
		return "usuario.id,tipoUsuario,usuario,senha,tecnico.id as idTecnico,nome,categoria,email,telefone,idFuncao";
	}

	public Usuario Login(String user, String pass) throws NoSuchAlgorithmException, SQLException {
		List<Usuario> usuario = select("", "usuario = '" + user + "' AND senha = '" + Util.MD5(pass) + "'");
		if (usuario.isEmpty() || usuario.size() > 1)
			return null;
		else
			return usuario.get(0);

	}

	public static void Initialize() {
		UsuarioRepository r;
		try {
			r = new UsuarioRepository();
			r.CreateTable();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Usuario findByTecnicoId(int id) throws SQLException {
		List<Usuario> usuario = select("", "idTecnico = " + id);
		if (usuario.isEmpty())
			return null;
		else
			return usuario.get(0);
	}

}
