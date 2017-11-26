package com.helpdesk.repository;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import com.helpdesk.models.Chamado;

public class ChamadoRepository extends repositoryBase<Chamado> {

	public ChamadoRepository() throws ClassNotFoundException {

	}

	@Override
	protected String defineTableName() {
		return "chamado";
	}

	@Override
	protected Map<String, SQLiteTypes> defineFields() {

		Map<String, SQLiteTypes> map = new HashMap<>();
		map.put("id", SQLiteTypes.INTERGER_NOT_NULL_PRIMARYKEY_AUTOINCREMENT);
		map.put("descricao", SQLiteTypes.STRING);
		map.put("categoria", SQLiteTypes.STRING);
		map.put("assunto", SQLiteTypes.STRING);
		map.put("situacao", SQLiteTypes.STRING);
		map.put("idCliente", SQLiteTypes.INTEGER);

		return map;

	}

	@Override
	public List<Chamado> findAll() throws SQLException {
		return select();
	}

	@Override
	protected Chamado fillObject(ResultSet result) throws SQLException {
		return new Chamado(result.getInt("id"), result.getString("descricao"), result.getString("categoria"),
				result.getString("assunto"), result.getString("situacao"), result.getInt("idCliente"));
	}

	@Override
	protected String[] getFieldsInsert() {

		List<String> list = new ArrayList<>();
		list.add("descricao");
		list.add("categoria");
		list.add("assunto");
		list.add("situacao");
		//list.add("idCliente");
		return list.toArray(new String[0]);
	}

	@Override
	protected Object[] getValueInsert(Chamado obj) {

		List<Object> list = new ArrayList<>();
		list.add(obj.getDescricao());
		list.add(obj.getCategoria());
		list.add(obj.getAssunto());
		list.add(obj.getSituacao());
	//	list.add(obj.getIdCliente());
		return list.toArray();
	}

	@Override
	protected String getPrimaryKeyField() {
		return "id";
	}

	@Override
	protected Map<String, Object> getUpdateValues(Chamado obj) {
		Map<String, Object> map = new HashMap();

		map.put("descricao", obj.getDescricao());
		map.put("categoria", obj.getCategoria());
		map.put("assunto", obj.getAssunto());
		map.put("situacao", obj.getSituacao());
		map.put("idCliente", obj.getIdCliente());

		return map;
	}

	@Override
	protected int getPrimaryKeyValue(Chamado obj) {
		return obj.getId();
	}

}
