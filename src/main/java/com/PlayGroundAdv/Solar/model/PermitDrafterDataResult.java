package com.PlayGroundAdv.Solar.model;

public class PermitDrafterDataResult {

	private String chooseScale;
	private Integer totalRoofSquareFootage;
	private Integer totalArraySectionCount;
	private String chooseScaleRackingLayout;
	private String chooseScaleElectricalLayout;
	private String uploadCommentsParcel;
	private String uploadCommentsPV;
	private String uploadCommentsRacking;
	private String uploadCommentsElectrical;
	private String uploadCommentsPlacard;
	private String uploadCommentsAutoCad;

	// 05/14/2019: CI : CR 2566 Planset New Cover Sheet & Notes Sheets
	private Integer stanchionQuantity;

	private String chooseScaleOther;
	private String chooseScaleArrayLayout;
	private String chooseScaleArrayOther;
	private String chooseScaleRackingOther;
	private String chooseScaleElectricalOther;
	private Boolean customizeScale;
	
	private String scalePV101;
	private String scalePV101Other;

	public PermitDrafterDataResult() {
		super();
	}

	public PermitDrafterDataResult(String chooseScale, Integer totalRoofSquareFootage, Integer totalArraySectionCount,
			String chooseScaleRackingLayout, String chooseScaleElectricalLayout, String uploadCommentsParcel,
			String uploadCommentsPV, String uploadCommentsRacking, String uploadCommentsElectrical,
			String uploadCommentsPlacard, String uploadCommentsAutoCad, Integer stanchionQuantity,
			String chooseScaleOther, String chooseScaleArrayLayout, String chooseScaleArrayOther,
			String chooseScaleRackingOther, String chooseScaleElectricalOther, Boolean customizeScale) {
		super();
		this.chooseScale = chooseScale;
		this.totalRoofSquareFootage = totalRoofSquareFootage;
		this.totalArraySectionCount = totalArraySectionCount;
		this.chooseScaleRackingLayout = chooseScaleRackingLayout;
		this.chooseScaleElectricalLayout = chooseScaleElectricalLayout;
		this.uploadCommentsParcel = uploadCommentsParcel;
		this.uploadCommentsPV = uploadCommentsPV;
		this.uploadCommentsRacking = uploadCommentsRacking;
		this.uploadCommentsElectrical = uploadCommentsElectrical;
		this.uploadCommentsPlacard = uploadCommentsPlacard;
		this.uploadCommentsAutoCad = uploadCommentsAutoCad;
		this.stanchionQuantity = stanchionQuantity;
		this.chooseScaleOther = chooseScaleOther;
		this.chooseScaleArrayLayout = chooseScaleArrayLayout;
		this.chooseScaleArrayOther = chooseScaleArrayOther;
		this.chooseScaleRackingOther = chooseScaleRackingOther;
		this.chooseScaleElectricalOther = chooseScaleElectricalOther;
		this.customizeScale = customizeScale;
	}

	public PermitDrafterDataResult(String chooseScale, Integer totalRoofSquareFootage, Integer totalArraySectionCount,
			String chooseScaleRackingLayout, String chooseScaleElectricalLayout, String uploadCommentsParcel,
			String uploadCommentsPV, String uploadCommentsRacking, String uploadCommentsElectrical,
			String uploadCommentsPlacard, String uploadCommentsAutoCad, Integer stanchionQuantity,
			String chooseScaleOther, String chooseScaleArrayLayout, String chooseScaleArrayOther,
			String chooseScaleRackingOther, String chooseScaleElectricalOther, Boolean customizeScale,
			String scalePV101, String scalePV101Other) {
		super();
		this.chooseScale = chooseScale;
		this.totalRoofSquareFootage = totalRoofSquareFootage;
		this.totalArraySectionCount = totalArraySectionCount;
		this.chooseScaleRackingLayout = chooseScaleRackingLayout;
		this.chooseScaleElectricalLayout = chooseScaleElectricalLayout;
		this.uploadCommentsParcel = uploadCommentsParcel;
		this.uploadCommentsPV = uploadCommentsPV;
		this.uploadCommentsRacking = uploadCommentsRacking;
		this.uploadCommentsElectrical = uploadCommentsElectrical;
		this.uploadCommentsPlacard = uploadCommentsPlacard;
		this.uploadCommentsAutoCad = uploadCommentsAutoCad;
		this.stanchionQuantity = stanchionQuantity;
		this.chooseScaleOther = chooseScaleOther;
		this.chooseScaleArrayLayout = chooseScaleArrayLayout;
		this.chooseScaleArrayOther = chooseScaleArrayOther;
		this.chooseScaleRackingOther = chooseScaleRackingOther;
		this.chooseScaleElectricalOther = chooseScaleElectricalOther;
		this.customizeScale = customizeScale;
		this.scalePV101 = scalePV101;
		this.scalePV101Other = scalePV101Other;
	}

	public String getUploadCommentsParcel() {
		return uploadCommentsParcel;
	}

	public void setUploadCommentsParcel(String uploadCommentsParcel) {
		this.uploadCommentsParcel = uploadCommentsParcel;
	}

	public String getUploadCommentsPV() {
		return uploadCommentsPV;
	}

	public void setUploadCommentsPV(String uploadCommentsPV) {
		this.uploadCommentsPV = uploadCommentsPV;
	}

	public String getUploadCommentsRacking() {
		return uploadCommentsRacking;
	}

	public void setUploadCommentsRacking(String uploadCommentsRacking) {
		this.uploadCommentsRacking = uploadCommentsRacking;
	}

	public String getUploadCommentsElectrical() {
		return uploadCommentsElectrical;
	}

	public void setUploadCommentsElectrical(String uploadCommentsElectrical) {
		this.uploadCommentsElectrical = uploadCommentsElectrical;
	}

	public String getUploadCommentsPlacard() {
		return uploadCommentsPlacard;
	}

	public void setUploadCommentsPlacard(String uploadCommentsPlacard) {
		this.uploadCommentsPlacard = uploadCommentsPlacard;
	}

	public String getUploadCommentsAutoCad() {
		return uploadCommentsAutoCad;
	}

	public void setUploadCommentsAutoCad(String uploadCommentsAutoCad) {
		this.uploadCommentsAutoCad = uploadCommentsAutoCad;
	}

	/**
	 * @return the chooseScale
	 */
	public String getChooseScale() {
		return chooseScale;
	}

	/**
	 * @return the totalRoofSquareFootage
	 */
	public Integer getTotalRoofSquareFootage() {
		return totalRoofSquareFootage;
	}

	/**
	 * @return the totalArraySectionCount
	 */
	public Integer getTotalArraySectionCount() {
		return totalArraySectionCount;
	}

	/**
	 * @return the chooseScaleRackingLayout
	 */
	public String getChooseScaleRackingLayout() {
		return chooseScaleRackingLayout;
	}

	/**
	 * @return the chooseScaleElectricalLayout
	 */
	public String getChooseScaleElectricalLayout() {
		return chooseScaleElectricalLayout;
	}

	/**
	 * @param chooseScale the chooseScale to set
	 */
	public void setChooseScale(String chooseScale) {
		this.chooseScale = chooseScale;
	}

	/**
	 * @param totalRoofSquareFootage the totalRoofSquareFootage to set
	 */
	public void setTotalRoofSquareFootage(Integer totalRoofSquareFootage) {
		this.totalRoofSquareFootage = totalRoofSquareFootage;
	}

	/**
	 * @param totalArraySectionCount the totalArraySectionCount to set
	 */
	public void setTotalArraySectionCount(Integer totalArraySectionCount) {
		this.totalArraySectionCount = totalArraySectionCount;
	}

	/**
	 * @param chooseScaleRackingLayout the chooseScaleRackingLayout to set
	 */
	public void setChooseScaleRackingLayout(String chooseScaleRackingLayout) {
		this.chooseScaleRackingLayout = chooseScaleRackingLayout;
	}

	/**
	 * @param chooseScaleElectricalLayout the chooseScaleElectricalLayout to set
	 */
	public void setChooseScaleElectricalLayout(String chooseScaleElectricalLayout) {
		this.chooseScaleElectricalLayout = chooseScaleElectricalLayout;
	}

	public Integer getStanchionQuantity() {
		return stanchionQuantity;
	}

	public void setStanchionQuantity(Integer stanchionQuantity) {
		this.stanchionQuantity = stanchionQuantity;
	}

	public String getChooseScaleOther() {
		return chooseScaleOther;
	}

	public void setChooseScaleOther(String chooseScaleOther) {
		this.chooseScaleOther = chooseScaleOther;
	}

	public String getChooseScaleArrayLayout() {
		return chooseScaleArrayLayout;
	}

	public void setChooseScaleArrayLayout(String chooseScaleArrayLayout) {
		this.chooseScaleArrayLayout = chooseScaleArrayLayout;
	}

	public String getChooseScaleArrayOther() {
		return chooseScaleArrayOther;
	}

	public void setChooseScaleArrayOther(String chooseScaleArrayOther) {
		this.chooseScaleArrayOther = chooseScaleArrayOther;
	}

	public String getChooseScaleRackingOther() {
		return chooseScaleRackingOther;
	}

	public void setChooseScaleRackingOther(String chooseScaleRackingOther) {
		this.chooseScaleRackingOther = chooseScaleRackingOther;
	}

	public String getChooseScaleElectricalOther() {
		return chooseScaleElectricalOther;
	}

	public void setChooseScaleElectricalOther(String chooseScaleElectricalOther) {
		this.chooseScaleElectricalOther = chooseScaleElectricalOther;
	}

	public Boolean getCustomizeScale() {
		return customizeScale;
	}

	public void setCustomizeScale(Boolean customizeScale) {
		this.customizeScale = customizeScale;
	}

	public String getScalePV101() {
		return scalePV101;
	}

	public void setScalePV101(String scalePV101) {
		this.scalePV101 = scalePV101;
	}

	public String getScalePV101Other() {
		return scalePV101Other;
	}

	public void setScalePV101Other(String scalePV101Other) {
		this.scalePV101Other = scalePV101Other;
	}

	@Override
	public String toString() {
		return "PermitDrafterDataResult [chooseScale=" + chooseScale + ", totalRoofSquareFootage="
				+ totalRoofSquareFootage + ", totalArraySectionCount=" + totalArraySectionCount
				+ ", chooseScaleRackingLayout=" + chooseScaleRackingLayout + ", chooseScaleElectricalLayout="
				+ chooseScaleElectricalLayout + ", uploadCommentsParcel=" + uploadCommentsParcel + ", uploadCommentsPV="
				+ uploadCommentsPV + ", uploadCommentsRacking=" + uploadCommentsRacking + ", uploadCommentsElectrical="
				+ uploadCommentsElectrical + ", uploadCommentsPlacard=" + uploadCommentsPlacard
				+ ", uploadCommentsAutoCad=" + uploadCommentsAutoCad + ", stanchionQuantity=" + stanchionQuantity + "]";
	}

}
