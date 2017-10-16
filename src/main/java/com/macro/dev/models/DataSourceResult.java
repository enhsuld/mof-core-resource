package com.macro.dev.models;

import org.json.JSONArray;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataSourceResult {

	private List<?> Data;
	private long Total;
	
	public List<?> getData() {
		return Data;
	}
	public void setData(List<?> data) {
		Data = data;
	}
	public long getTotal() {
		return Total;
	}
	public void setTotal(long count) {
		Total = count;
	}
	
}
