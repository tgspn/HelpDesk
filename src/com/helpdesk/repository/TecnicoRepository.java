package com.helpdesk.repository;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.helpdesk.models.Tecnico;
import com.helpdesk.models.Usuario;

public class TecnicoRepository extends repositoryBase<Tecnico> {

	public TecnicoRepository() throws ClassNotFoundException {
		super();
		
	}

	@Override
	protected String[] getFieldsInsert() {
		List<String> list = new ArrayList<>();
		list.add("nome");
		list.add("idFuncao");
		return list.toArray(new String[0]);
	}

	@Override
	protected String getPrimaryKeyField() {
		return "id";
	}

	@Override
	protected int getPrimaryKeyValue(Tecnico obj) {
		return obj.getId();
	}
	@Override
	protected void setPrimaryKeyValue(Tecnico obj, int value) {
		obj.setId(value);
	}
	@Override
	protected Object[] getValueInsert(Tecnico obj) {
		List<Object> list = new ArrayList<>();
		list.add(obj.getNome());
		list.add(obj.getIdFuncao());

		return list.toArray();
	}

	@Override
	protected Map<String, Object> getUpdateValues(Tecnico obj) {
		Map<String, Object> map = new HashMap();

		map.put("idFuncao", obj.getIdFuncao());
		map.put("nome", obj.getNome());
		return map;
	}

	@Override
	protected Tecnico fillObject(ResultSet result) throws SQLException {

		return new Tecnico(result.getInt("id"), result.getString("nome"), result.getInt("idFuncao"));

	}

	@Override
	protected Map<String, SQLiteTypes> defineFields() {
		Map<String, SQLiteTypes> map = new HashMap<>();
		map.put("id", SQLiteTypes.INTERGER_NOT_NULL_PRIMARYKEY_AUTOINCREMENT);
		map.put("nome", SQLiteTypes.STRING);
		map.put("idFuncao", SQLiteTypes.INTEGER);
		return map;
	}

	@Override
	protected String defineTableName() {
		return "tecnico";
	}

	@Override
	protected String defineDefaultJoin() {
		return "";
	}

	@Override
	protected String defineDefaultParams() {
		return "*";
	}

	public static void Initialize() {
		try {
			TecnicoRepository r=new TecnicoRepository();
			r.CreateTable();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
