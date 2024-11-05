package com.PlayGroundAdv.Solar.model;

public class DrafterInformationModel {

	private Long id;
	private Boolean needForArackingLayD;
	private Boolean roofFeatCallOut;
	private Boolean draftSepSitePlan;
	private Boolean showingTransfLocation;
	private Boolean dimenrequirOnArr;
	private Long idPermit;

	public DrafterInformationModel() {
		super();
	}

	public DrafterInformationModel(Long id, Boolean needForArackingLayD, Boolean roofFeatCallOut,
			Boolean draftSepSitePlan, Boolean showingTransfLocation, Boolean dimenrequirOnArr, Long idPermit) {
		super();
		this.id = id;
		this.needForArackingLayD = needForArackingLayD;
		this.roofFeatCallOut = roofFeatCallOut;
		this.draftSepSitePlan = draftSepSitePlan;
		this.showingTransfLocation = showingTransfLocation;
		this.dimenrequirOnArr = dimenrequirOnArr;
		this.idPermit = idPermit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getNeedForArackingLayD() {
		return needForArackingLayD;
	}

	public void setNeedForArackingLayD(Boolean needForArackingLayD) {
		this.needForArackingLayD = needForArackingLayD;
	}

	public Boolean getRoofFeatCallOut() {
		return roofFeatCallOut;
	}

	public void setRoofFeatCallOut(Boolean roofFeatCallOut) {
		this.roofFeatCallOut = roofFeatCallOut;
	}

	public Boolean getDraftSepSitePlan() {
		return draftSepSitePlan;
	}

	public void setDraftSepSitePlan(Boolean draftSepSitePlan) {
		this.draftSepSitePlan = draftSepSitePlan;
	}

	public Boolean getShowingTransfLocation() {
		return showingTransfLocation;
	}

	public void setShowingTransfLocation(Boolean showingTransfLocation) {
		this.showingTransfLocation = showingTransfLocation;
	}

	public Boolean getDimenrequirOnArr() {
		return dimenrequirOnArr;
	}

	public void setDimenrequirOnArr(Boolean dimenrequirOnArr) {
		this.dimenrequirOnArr = dimenrequirOnArr;
	}

	public Long getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(Long idPermit) {
		this.idPermit = idPermit;
	}

}
