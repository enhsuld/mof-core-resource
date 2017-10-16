package com.macro.dev.services;

import java.util.HashMap;
import java.util.List;

import com.macro.dev.constant.DatatableInput;
import com.macro.dev.repository.mybatis.DatatableMapper;

public class DatatableService {

	private DatatableMapper databaseMapper;
	private final String SEPARATOR = " OR ";
	
	public DatatableService(DatatableMapper databaseMapper) {
		this.databaseMapper = databaseMapper;
	}

	public Integer getSize(DatatableInput datatableInput) {
		return databaseMapper.count(datatableInput);
	}

	private String mountLikeClause(String column, String search) {
		return " "+column+" like '%"+search+"%'"+SEPARATOR;  
	}

	public  List<HashMap<String, Object>> getFromDatatable(DatatableInput datatableInput) {
		if (datatableInput.getSearch() != null && datatableInput.getSearch().length() >= 1) {
			StringBuilder fullsearch = new StringBuilder(" WHERE ");
			datatableInput.getColumns().forEach(x -> {fullsearch.append(mountLikeClause(x,datatableInput.getSearch()));});
			datatableInput.setFullsearch(fullsearch.substring(0,fullsearch.length() - SEPARATOR.length()));
		}
		return databaseMapper.getDatatable(datatableInput);
	}

	public Integer getPartialSize(DatatableInput datatableInput) {
		return databaseMapper.getPartialSize(datatableInput);
	}

}
