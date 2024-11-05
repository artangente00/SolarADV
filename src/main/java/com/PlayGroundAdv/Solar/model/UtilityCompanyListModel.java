package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;

public class UtilityCompanyListModel {
	private List<ElectricalUtilityEntity> utilityCompanyList;
	private Integer totalPages;
	private Long totalRecords;
	
	public UtilityCompanyListModel() {
		super();
	}

	public UtilityCompanyListModel(List<ElectricalUtilityEntity> utilityCompanyList, Integer totalPages, Long totalRecords) {
		super();
		this.utilityCompanyList = utilityCompanyList;
		this.totalPages = totalPages;
		this.totalRecords = totalRecords;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<ElectricalUtilityEntity> getUtilityCompanyList() {
		return utilityCompanyList;
	}

	public void setUtilityCompanyList(List<ElectricalUtilityEntity> utilityCompanyList) {
		this.utilityCompanyList = utilityCompanyList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
