package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class SsheetSpacingMappingModel {

	private String detailsHeading;
	private Long id;
	private Date lastUpdate;
	private Long ssheet;
	private String pdfname;
	private String quadNumber;
	private String rafterTrussSpacing;
	private String roofType;
	private String roofTypeOther;
	private String sheetNumber;
	private String stanchionMaxSpacing;
	private String stanchionType;
	private Boolean flashing;

	public SsheetSpacingMappingModel() {
		super();
	}

	public SsheetSpacingMappingModel(String detailsHeading, Long id, Date lastUpdate, Long ssheet, String pdfname,
			String quadNumber, String rafterTrussSpacing, String roofType, String roofTypeOther, String sheetNumber,
			String stanchionMaxSpacing, String stanchionType,Boolean flashing) {
		super();
		this.detailsHeading = detailsHeading;
		this.id = id;
		this.lastUpdate = lastUpdate;
		this.ssheet = ssheet;
		this.pdfname = pdfname;
		this.quadNumber = quadNumber;
		this.rafterTrussSpacing = rafterTrussSpacing;
		this.roofType = roofType;
		this.roofTypeOther = roofTypeOther;
		this.sheetNumber = sheetNumber;
		this.stanchionMaxSpacing = stanchionMaxSpacing;
		this.stanchionType = stanchionType;
		this.flashing = flashing;
	}

	public String getDetailsHeading() {
		return detailsHeading;
	}

	public void setDetailsHeading(String detailsHeading) {
		this.detailsHeading = detailsHeading;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getSsheet() {
		return ssheet;
	}

	public void setSsheet(Long ssheet) {
		this.ssheet = ssheet;
	}

	public String getQuadNumber() {
		return quadNumber;
	}

	public void setQuadNumber(String quadNumber) {
		this.quadNumber = quadNumber;
	}

	public String getRafterTrussSpacing() {
		return rafterTrussSpacing;
	}

	public void setRafterTrussSpacing(String rafterTrussSpacing) {
		this.rafterTrussSpacing = rafterTrussSpacing;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public String getRoofTypeOther() {
		return roofTypeOther;
	}

	public void setRoofTypeOther(String roofTypeOther) {
		this.roofTypeOther = roofTypeOther;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public String getStanchionMaxSpacing() {
		return stanchionMaxSpacing;
	}

	public void setStanchionMaxSpacing(String stanchionMaxSpacing) {
		this.stanchionMaxSpacing = stanchionMaxSpacing;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}
	
	public String getStanchionType() {
		return stanchionType;
	}

	public void setStanchionType(String stanchionType) {
		this.stanchionType = stanchionType;
	}
	

	public Boolean getFlashing() {
		return flashing;
	}

	public void setFlashing(Boolean flashing) {
		this.flashing = flashing;
	}

	@Override
	public String toString() {
		return "SsheetSpacingMappingModel [detailsHeading=" + detailsHeading + ", id=" + id + ", lastUpdate="
				+ lastUpdate + ", ssheet=" + ssheet + ", pdfname=" + pdfname + ", quadNumber=" + quadNumber
				+ ", rafterTrussSpacing=" + rafterTrussSpacing + ", roofType=" + roofType + ", roofTypeOther="
				+ roofTypeOther + ", sheetNumber=" + sheetNumber + ", stanchionMaxSpacing=" + stanchionMaxSpacing + "]";
	}

}
