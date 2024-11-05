package com.PlayGroundAdv.Solar.model;

import java.util.List;

public class TiltLegsListModel {
	private List<SearchTiltLegsResult> tiltLegsList;
	private Integer totalPages;
	private Long totalRecords;
	
	public TiltLegsListModel() {
		super();
	}

	public TiltLegsListModel(List<SearchTiltLegsResult> tiltLegsList, Integer totalPages, Long totalRecords) {
		super();
		this.tiltLegsList = tiltLegsList;
		this.totalPages = totalPages;
		this.totalRecords= totalRecords;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<SearchTiltLegsResult> getTiltLegsList() {
		return tiltLegsList;
	}

	public void setTiltLegsList(List<SearchTiltLegsResult> tiltLegsList) {
		this.tiltLegsList = tiltLegsList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
