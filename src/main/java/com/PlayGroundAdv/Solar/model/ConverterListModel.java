package com.PlayGroundAdv.Solar.model;

import java.util.List;

public class ConverterListModel {

	private List<SearchConverterResult> converterList;
	private Integer totalPages;
	private Long totalRecords;
	
	public ConverterListModel() {
		super();
	}

	public ConverterListModel(List<SearchConverterResult> converterList, Integer totalPages,Long totalRecords) {
		super();
		this.converterList = converterList;
		this.totalPages = totalPages;
		this.totalRecords = totalRecords;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<SearchConverterResult> getConverterList() {
		return converterList;
	}

	public void setConverterList(List<SearchConverterResult> converterList) {
		this.converterList = converterList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
