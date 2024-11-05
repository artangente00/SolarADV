package com.PlayGroundAdv.Solar.model;

public class PlansetRevisionIndexModel {

	public String pageNumber;
	public Integer pageIndex;

	public PlansetRevisionIndexModel() {
		super();
	}

	public PlansetRevisionIndexModel(String pageNumber, Integer pageIndex) {
		super();
		this.pageNumber = pageNumber;
		this.pageIndex = pageIndex;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

}
