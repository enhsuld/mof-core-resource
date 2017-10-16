package com.macro.dev.constant;

import java.util.List;

public class DatatableInput {
	private Integer start;
	private Integer length;
	private Integer draw;
	private String search;
	private String service;
	private String order;
	private String custom;
	private String orderdirection;
	private String fullsearch;
	private List<String> columns;
	
	
	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getFullsearch() {
		return fullsearch;
	}

	public void setFullsearch(String fullsearch) {
		this.fullsearch = fullsearch;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderdirection() {
		return orderdirection;
	}

	public void setOrderdirection(String orderdirection) {
		this.orderdirection = orderdirection;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}
}
