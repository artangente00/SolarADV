package com.PlayGroundAdv.Solar.model;

public class SelectDrafterSheetModel {
	private Integer pageNumber;
	private String pageSheet;

	public SelectDrafterSheetModel() {
		super();
	}

	public SelectDrafterSheetModel(Integer pageNumber, String pageSheet) {
		super();
		this.pageNumber = pageNumber;
		this.pageSheet = pageSheet;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSheet() {
		return pageSheet;
	}

	public void setPageSheet(String pageSheet) {
		this.pageSheet = pageSheet;
	}

}
