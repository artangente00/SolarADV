package com.PlayGroundAdv.Solar.model;

public class SpacingDetailsMappingModel {

	public String roofType;
	public String roofTypeOther;
	public String rafterTrussSpacing;
	public String stanchionMaxSpacing;
	public String sheetNumber;
	public String quadNumber;
	public String detailsHeading;
	public String deletedBy;
	public Long ssheet;
	private String pdfname;
	private Boolean uploadSheet;
	public String stanchionType;
	public Boolean flashing;

	public SpacingDetailsMappingModel() {
		super();
	}

	public SpacingDetailsMappingModel(String roofType, String roofTypeOther, String rafterTrussSpacing,
			String stanchionMaxSpacing, String sheetNumber, String quadNumber, String detailsHeading, String deletedBy,
			Long ssheet, String pdfname, Boolean uploadSheet, String stanchionType,Boolean flashing) {
		super();
		this.roofType = roofType;
		this.roofTypeOther = roofTypeOther;
		this.rafterTrussSpacing = rafterTrussSpacing;
		this.stanchionMaxSpacing = stanchionMaxSpacing;
		this.sheetNumber = sheetNumber;
		this.quadNumber = quadNumber;
		this.detailsHeading = detailsHeading;
		this.deletedBy = deletedBy;
		this.ssheet = ssheet;
		this.pdfname = pdfname;
		this.uploadSheet = uploadSheet;
		this.stanchionType = stanchionType;
		this.flashing = flashing;
	}

	public Boolean getFlashing() {
		return flashing;
	}

	public void setFlashing(Boolean flashing) {
		this.flashing = flashing;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public String getRafterTrussSpacing() {
		return rafterTrussSpacing;
	}

	public void setRafterTrussSpacing(String rafterTrussSpacing) {
		this.rafterTrussSpacing = rafterTrussSpacing;
	}

	public String getStanchionMaxSpacing() {
		return stanchionMaxSpacing;
	}

	public void setStanchionMaxSpacing(String stanchionMaxSpacing) {
		this.stanchionMaxSpacing = stanchionMaxSpacing;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public String getQuadNumber() {
		return quadNumber;
	}

	public void setQuadNumber(String quadNumber) {
		this.quadNumber = quadNumber;
	}

	public String getDetailsHeading() {
		return detailsHeading;
	}

	public void setDetailsHeading(String detailsHeading) {
		this.detailsHeading = detailsHeading;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Long getSsheet() {
		return ssheet;
	}

	public void setSsheet(Long ssheet) {
		this.ssheet = ssheet;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public Boolean getUploadSheet() {
		return uploadSheet;
	}

	public void setUploadSheet(Boolean uploadSheet) {
		this.uploadSheet = uploadSheet;
	}

	public String getRoofTypeOther() {
		return roofTypeOther;
	}

	public void setRoofTypeOther(String roofTypeOther) {
		this.roofTypeOther = roofTypeOther;
	}
	
	public String getStanchionType() {
		return stanchionType;
	}

	public void setStanchionType(String stanchionType) {
		this.stanchionType = stanchionType;
	}

	@Override
	public String toString() {
		return "SpacingDetailsMappingModel [roofType=" + roofType + ", rafterTrussSpacing=" + rafterTrussSpacing
				+ ", stanchionMaxSpacing=" + stanchionMaxSpacing + ", sheetNumber=" + sheetNumber + ", quadNumber="
				+ quadNumber + ", detailsHeading=" + detailsHeading + ", deletedBy=" + deletedBy + ", ssheet=" + ssheet
				+ ", pdfname=" + pdfname + ", uploadSheet=" + uploadSheet + "]";
	}

}
