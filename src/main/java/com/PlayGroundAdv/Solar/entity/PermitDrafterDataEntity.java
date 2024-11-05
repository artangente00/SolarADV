package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "PermitDrafterDataEntity")
public class PermitDrafterDataEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence22", sequenceName = "hibernate_sequence22", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence22")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="SCALE")
	private String scale;
	
	@Column(name="SCALE_RACKING_LAYOUT")
	private String scalerackingLayout;
	
	@Column(name="SCALE_ELECTRICAL_LAYOUT")
	private String scaleelectricalLayout;
	
	@Column(name="TOTAL_ROOF_SQUARE_FOOTAGE")
	private Integer totalRoofSquareFootage;
	
	@Column(name="TOTAL_ARRAY_SECTION_COUNT")
	private Integer totalArraySectionCount;
	
	
	@Column(name="PARCEL_MAP_Name")
	private String parcelMapName;
	
	
	@Column(name="PV_ARRAY_ALYOUT_NAME")
	private String pvArrayLayoutName;
	
	
	@Column(name="RACKING_LAYOUT_NAME")
	private String rackingLayoutName;
	
	@Column(name="ELECTRICAL_LAYOUT_NAME")
	private String electricalLayoutName;
	
	
	@Column(name="PLACARD_LAYOUT_NAME")
	private String placardLayoutName;
	
	
	@Column(name="AUTOCAD_FILE_NAME")
	private String autocadFileName;
	
	@Column(name="SKETCH_FILE_NAME")
	private String sketechFileName;

	
	@Column(name="IMAGE_NOTE_SKETCH_NAME")
	private String imageNoteSketchName;
	
	
	@Column(name="GOOGLE_MAP_IMAGE_NAME")
	private String googleMapImageName;
	
	
	@Column(name="NEAR_MAP_IMAGE_NAME")
	private String nearMapImageName;

	
	
	
	@Column(name="BOS_IMAGE_NAME")
	private String bosImageName;
	
	
	@Column(name="nameRFI")
	private String nameRFI; 
	
	
	@Column(name="UTILITY_BILL_PDF_NAME")
	private String  utilityBillPdfName;
	
	
	
	@Column(name="LOAD_JUSTIFICATION_NAME")
	private String  loadJustificationName;
	

	
	
	
	@Column(name="ADRESS_SUBDIVISION_FILE_Name")
	private String  adresseSubdivisionFileName;
	
	
	
	@Column(name="SWITCHGEAR_CUTSHEETS_FILE_NAME")
	private String  switchgearCutsheetsFileName;
	
	
	
	@Column(name="PROPORSALE_INTERCONNECTION_FILE_NAME")
	private String  proporsalInterconnectionFileName;
	
	
	@Column(name="EXISTING_SYSTEM_FILE_NAME")
	private String  existingSystemeFileName;
	
	
	@Column(name="LAYOUT_SKETCH_IMAGE_NAME")
	private String  layoutSketchImageName;
	
	@Column(name="uploadCommentsParcel")
	private String  uploadCommentsParcel;
	
	@Column(name="uploadCommentsPV")
	private String  uploadCommentsPV;
	
	@Column(name="uploadCommentsRacking")
	private String  uploadCommentsRacking;
	
	@Column(name="uploadCommentsElectrical")
	private String  uploadCommentsElectrical;
	
	@Column(name="uploadCommentsPlacard")
	private String  uploadCommentsPlacard;
	
	@Column(name="uploadCommentsAutoCad")
	private String  uploadCommentsAutoCad;
	
	
	@Column(name="STANCHION_QUANTITY")
	private Integer stanchionQuantity;
	
	@Column(name="CHOOSE_SCALE_OTHER")
	private String chooseScaleOther;
	
	@Column(name="CHOOSE_SCALE_ARRAY_LAYOUT")
	private String chooseScaleArrayLayout;
	
	@Column(name="CHOOSE_SCALE_ARRAY_OTHER")
	private String chooseScaleArrayOther;
	
	@Column(name="CHOOSE_SCALE_RACKING_OTHER")
	private String chooseScaleRackingOther;
	
	@Column(name="CHOOSE_SCALE_ELECTRICAL_OTHER")
	private String chooseScaleElectricalOther;
	
	@Column(name="CUSTOMIZE_SCALE")
	private Boolean customizeScale;
	
	@Column(name="SCALE_PV101")
	private String scalePV101;
	
	@Column(name="SCALE_PV101_OTHER")
	private String scalePV101Other;
	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return scale;
	}

	/**
	 * @return the scalerackingLayout
	 */
	public String getScalerackingLayout() {
		return scalerackingLayout;
	}

	/**
	 * @return the scaleelectricalLayout
	 */
	public String getScaleelectricalLayout() {
		return scaleelectricalLayout;
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
	 * @return the parcelMap
	 */

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}

	/**
	 * @param scalerackingLayout the scalerackingLayout to set
	 */
	public void setScalerackingLayout(String scalerackingLayout) {
		this.scalerackingLayout = scalerackingLayout;
	}

	/**
	 * @param scaleelectricalLayout the scaleelectricalLayout to set
	 */
	public void setScaleelectricalLayout(String scaleelectricalLayout) {
		this.scaleelectricalLayout = scaleelectricalLayout;
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
	 * @param parcelMap the parcelMap to set
	 */

	public String getParcelMapName() {
		return parcelMapName;
	}

	public void setParcelMapName(String parcelMapName) {
		this.parcelMapName = parcelMapName;
	}

	public String getPvArrayLayoutName() {
		return pvArrayLayoutName;
	}

	public void setPvArrayLayoutName(String pvArrayLayoutName) {
		this.pvArrayLayoutName = pvArrayLayoutName;
	}

	public String getRackingLayoutName() {
		return rackingLayoutName;
	}

	public void setRackingLayoutName(String rackingLayoutName) {
		this.rackingLayoutName = rackingLayoutName;
	}

	public String getElectricalLayoutName() {
		return electricalLayoutName;
	}

	public void setElectricalLayoutName(String electricalLayoutName) {
		this.electricalLayoutName = electricalLayoutName;
	}

	public String getPlacardLayoutName() {
		return placardLayoutName;
	}

	public void setPlacardLayoutName(String placardLayoutName) {
		this.placardLayoutName = placardLayoutName;
	}

	public String getAutocadFileName() {
		return autocadFileName;
	}

	public void setAutocadFileName(String autocadFileName) {
		this.autocadFileName = autocadFileName;
	}

	public String getSketechFileName() {
		return sketechFileName;
	}

	public void setSketechFileName(String sketechFileName) {
		this.sketechFileName = sketechFileName;
	}

	public String getImageNoteSketchName() {
		return imageNoteSketchName;
	}

	public void setImageNoteSketchName(String imageNoteSketchName) {
		this.imageNoteSketchName = imageNoteSketchName;
	}

	public String getGoogleMapImageName() {
		return googleMapImageName;
	}

	public void setGoogleMapImageName(String googleMapImageName) {
		this.googleMapImageName = googleMapImageName;
	}

	public String getNearMapImageName() {
		return nearMapImageName;
	}

	public void setNearMapImageName(String nearMapImageName) {
		this.nearMapImageName = nearMapImageName;
	}

	public String getBosImageName() {
		return bosImageName;
	}

	public void setBosImageName(String bosImageName) {
		this.bosImageName = bosImageName;
	}

	public String getUtilityBillPdfName() {
		return utilityBillPdfName;
	}

	public void setUtilityBillPdfName(String utilityBillPdfName) {
		this.utilityBillPdfName = utilityBillPdfName;
	}

	public String getLoadJustificationName() {
		return loadJustificationName;
	}

	public void setLoadJustificationName(String loadJustificationName) {
		this.loadJustificationName = loadJustificationName;
	}

	public String getAdresseSubdivisionFileName() {
		return adresseSubdivisionFileName;
	}

	public void setAdresseSubdivisionFileName(String adresseSubdivisionFileName) {
		this.adresseSubdivisionFileName = adresseSubdivisionFileName;
	}

	public String getSwitchgearCutsheetsFileName() {
		return switchgearCutsheetsFileName;
	}

	public void setSwitchgearCutsheetsFileName(String switchgearCutsheetsFileName) {
		this.switchgearCutsheetsFileName = switchgearCutsheetsFileName;
	}

	public String getProporsalInterconnectionFileName() {
		return proporsalInterconnectionFileName;
	}

	public void setProporsalInterconnectionFileName(String proporsalInterconnectionFileName) {
		this.proporsalInterconnectionFileName = proporsalInterconnectionFileName;
	}

	public String getExistingSystemeFileName() {
		return existingSystemeFileName;
	}

	public void setExistingSystemeFileName(String existingSystemeFileName) {
		this.existingSystemeFileName = existingSystemeFileName;
	}

	public String getLayoutSketchImageName() {
		return layoutSketchImageName;
	}

	public void setLayoutSketchImageName(String layoutSketchImageName) {
		this.layoutSketchImageName = layoutSketchImageName;
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameRFI() {
		return nameRFI;
	}

	public void setNameRFI(String nameRFI) {
		this.nameRFI = nameRFI;
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
	
}
