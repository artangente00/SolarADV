package com.PlayGroundAdv.Solar.model;

public class PermitEditStatusModel {

	private Boolean opened;
	private Long openedBy;
	private Boolean hasEditRequest;
	private Boolean hasEditAccess;

	public PermitEditStatusModel() {
		super();
	}

	public PermitEditStatusModel(Boolean opened, Long openedBy, Boolean hasEditRequest, Boolean hasEditAccess) {
		super();
		this.opened = opened;
		this.openedBy = openedBy;
		this.hasEditRequest = hasEditRequest;
		this.hasEditAccess = hasEditRequest;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Long getOpenedBy() {
		return openedBy;
	}

	public void setOpenedBy(Long openedBy) {
		this.openedBy = openedBy;
	}

	public Boolean getHasEditRequest() {
		return hasEditRequest;
	}

	public void setHasEditRequest(Boolean hasEditRequest) {
		this.hasEditRequest = hasEditRequest;
	}

	public Boolean getHasEditAccess() {
		return hasEditAccess;
	}

	public void setHasEditAccess(Boolean hasEditAccess) {
		this.hasEditAccess = hasEditAccess;
	}

	@Override
	public String toString() {
		return "PermitEditStatusModel [opened=" + opened + ", openedBy=" + openedBy + ", hasEditRequest="
				+ hasEditRequest + ", hasEditAccess=" + hasEditAccess + "]";
	}

}
