package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

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
@Table(name = "PermitAdvEntity")
public class PermitAdvEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "hibernate_sequence9", sequenceName = "hibernate_sequence9", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence9")
	private Long id;

	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;

	@Column(name = "WIND_SPEED")
	private String windSpeed;

	@Column(name = "SNOW_LOAD")
	private String snowLoad;

	@Column(name = "GOOGLE_IMAGE")
	private String googleImage;

	@Column(name = "MAP_IMAGE")
	private String mapImage;

	@Column(name = "P_V_RAIL_QTE")
	private String pVRailQte;

	@Column(name = "P_V_RAIL_LENGTH")
	private String pVRailLength;

	@Column(name = "STANCHION_QTE")
	private String stanchionQte;

	@Column(name = "STANCHION_LENGTH")
	private String stanchionLength;

	@Column(name = "SPLICE_QTE")
	private String spliceQte;

	@Column(name = "SPLICE_LENGTH")
	private String spliceLength;

	@Column(name = "S200_QTE")
	private String s200Qte;

	@Column(name = "S200_LENGTH")
	private String s200Length;

	@Column(name = "PV1")
	private String pv1;

	@Column(name = "CUSTOMERS_SERVICE_AGREEMENT_ID_NUMBER")
	private String customersServiceAgreementIDNumber;

	@Column(name = "CUSTOMERS_RATE_SCHEDULE")
	private String customersRateSchedule;

	@Column(name = "ENGINEERING_FIRM")
	private String engineeringFirm;

	@Column(name = "CUSTOMERS_ACCOUNT_NUMBER")
	private String customersAccountNumber;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	// CR-1231- Add new field Other wind speed
	@Column(name = "WIN_SPEED_OTHER")
	private String windSpeedOther;

	// CR-1231- Add new field Other snow load
	@Column(name = "SNOW_LOAD_OTHER")
	private String snowLoadOther;

	@Column(name = "uploadCommentsGoogle")
	private String uploadCommentsGoogle;

	@Column(name = "uploadCommentsNearMap")
	private String uploadCommentsNearMap;

	@Column(name = "GOOGLE_ZOOM",columnDefinition = "integer default 18")
	private Integer googleZoom;

	// CR-1181- Update Edit Project Inputs
	@Column(name = "MODULE_LAYOUT")
	private String moduleLayout;

	@Column(name = "SIZE_OF_PIPE")
	private String sizeOfPipe;

	@Column(name = "THICKNESS_OF_PIPE")
	private String thicknessOfPipe;

	@Column(name = "BRACED_UNBRACED")
	private String bracedUnbraced;

	@Column(name = "FOOTING_DIAMETER")
	private String footingDiameter;

	@Column(name = "MODULE_LAYOUT_OTHER")
	private String moduleLayoutOther;

	@Column(name = "SIZE_OF_PIPE_OTHER")
	private String sizeOfPipeOther;

	@Column(name = "THICKNESS_OF_PIPE_OTHER")
	private String thicknessOfPipeOther;

	@Column(name = "FOOTING_DIAMETER_OTHER")
	private String footingDiameterOther;

	@Column(name = "OPEN_TLD_LIBRARY")
	private Boolean openTldLibrary;

	@Column(name = "TLD_SHORT_LIST")
	private Boolean tldShortList;

	@Column(name = "TLD_LIST")
	private String tldList;

	@Column(name = "R_SHEET_LIST")
	private String rSheetList;

	@Column(name = "BRACED_UNBRACED_OTHER")
	private String bracedUnbracedOther;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @return the windSpeed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * @return the snowLoad
	 */
	public String getSnowLoad() {
		return snowLoad;
	}

	/**
	 * @param snowLoad the snowLoad to set
	 */
	public void setSnowLoad(String snowLoad) {
		this.snowLoad = snowLoad;
	}

	/**
	 * @return the googleImage
	 */
	public String getGoogleImage() {
		return googleImage;
	}

	/**
	 * @param googleImage the googleImage to set
	 */
	public void setGoogleImage(String googleImage) {
		this.googleImage = googleImage;
	}

	/**
	 * @return the mapImage
	 */
	public String getMapImage() {
		return mapImage;
	}

	/**
	 * @param mapImage the mapImage to set
	 */
	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}

	/**
	 * @return the pVRailQte
	 */
	public String getpVRailQte() {
		return pVRailQte;
	}

	/**
	 * @param pVRailQte the pVRailQte to set
	 */
	public void setpVRailQte(String pVRailQte) {
		this.pVRailQte = pVRailQte;
	}

	/**
	 * @return the pVRailLength
	 */
	public String getpVRailLength() {
		return pVRailLength;
	}

	/**
	 * @param pVRailLength the pVRailLength to set
	 */
	public void setpVRailLength(String pVRailLength) {
		this.pVRailLength = pVRailLength;
	}

	/**
	 * @return the stanchionQte
	 */
	public String getStanchionQte() {
		return stanchionQte;
	}

	/**
	 * @param stanchionQte the stanchionQte to set
	 */
	public void setStanchionQte(String stanchionQte) {
		this.stanchionQte = stanchionQte;
	}

	/**
	 * @return the stanchionLength
	 */
	public String getStanchionLength() {
		return stanchionLength;
	}

	/**
	 * @param stanchionLength the stanchionLength to set
	 */
	public void setStanchionLength(String stanchionLength) {
		this.stanchionLength = stanchionLength;
	}

	/**
	 * @return the spliceQte
	 */
	public String getSpliceQte() {
		return spliceQte;
	}

	/**
	 * @param spliceQte the spliceQte to set
	 */
	public void setSpliceQte(String spliceQte) {
		this.spliceQte = spliceQte;
	}

	/**
	 * @return the spliceLength
	 */
	public String getSpliceLength() {
		return spliceLength;
	}

	/**
	 * @param spliceLength the spliceLength to set
	 */
	public void setSpliceLength(String spliceLength) {
		this.spliceLength = spliceLength;
	}

	/**
	 * @return the s200Qte
	 */
	public String getS200Qte() {
		return s200Qte;
	}

	/**
	 * @param s200Qte the s200Qte to set
	 */
	public void setS200Qte(String s200Qte) {
		this.s200Qte = s200Qte;
	}

	/**
	 * @return the s200Length
	 */
	public String getS200Length() {
		return s200Length;
	}

	/**
	 * @param s200Length the s200Length to set
	 */
	public void setS200Length(String s200Length) {
		this.s200Length = s200Length;
	}

	/**
	 * @return the pv1
	 */
	public String getPv1() {
		return pv1;
	}

	/**
	 * @param pv1 the pv1 to set
	 */
	public void setPv1(String pv1) {
		this.pv1 = pv1;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the customersServiceAgreementIDNumber
	 */
	public String getCustomersServiceAgreementIDNumber() {
		return customersServiceAgreementIDNumber;
	}

	/**
	 * @param customersServiceAgreementIDNumber the
	 *                                          customersServiceAgreementIDNumber to
	 *                                          set
	 */
	public void setCustomersServiceAgreementIDNumber(String customersServiceAgreementIDNumber) {
		this.customersServiceAgreementIDNumber = customersServiceAgreementIDNumber;
	}

	/**
	 * @return the customersRateSchedule
	 */
	public String getCustomersRateSchedule() {
		return customersRateSchedule;
	}

	/**
	 * @param customersRateSchedule the customersRateSchedule to set
	 */
	public void setCustomersRateSchedule(String customersRateSchedule) {
		this.customersRateSchedule = customersRateSchedule;
	}

	/**
	 * @return the engineeringFirm
	 */
	public String getEngineeringFirm() {
		return engineeringFirm;
	}

	/**
	 * @param engineeringFirm the engineeringFirm to set
	 */
	public void setEngineeringFirm(String engineeringFirm) {
		this.engineeringFirm = engineeringFirm;
	}

	/**
	 * @return the customersAccountNumber
	 */
	public String getCustomersAccountNumber() {
		return customersAccountNumber;
	}

	/**
	 * @param customersAccountNumber the customersAccountNumber to set
	 */
	public void setCustomersAccountNumber(String customersAccountNumber) {
		this.customersAccountNumber = customersAccountNumber;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getWindSpeedOther() {
		return windSpeedOther;
	}

	public void setWindSpeedOther(String windSpeedOther) {
		this.windSpeedOther = windSpeedOther;
	}

	public String getSnowLoadOther() {
		return snowLoadOther;
	}

	public void setSnowLoadOther(String snowLoadOther) {
		this.snowLoadOther = snowLoadOther;
	}

	public String getUploadCommentsGoogle() {
		return uploadCommentsGoogle;
	}

	public void setUploadCommentsGoogle(String uploadCommentsGoogle) {
		this.uploadCommentsGoogle = uploadCommentsGoogle;
	}

	public String getUploadCommentsNearMap() {
		return uploadCommentsNearMap;
	}

	public void setUploadCommentsNearMap(String uploadCommentsNearMap) {
		this.uploadCommentsNearMap = uploadCommentsNearMap;
	}

	public String getModuleLayout() {
		return moduleLayout;
	}

	public void setModuleLayout(String moduleLayout) {
		this.moduleLayout = moduleLayout;
	}

	public String getSizeOfPipe() {
		return sizeOfPipe;
	}

	public void setSizeOfPipe(String sizeOfPipe) {
		this.sizeOfPipe = sizeOfPipe;
	}

	public String getThicknessOfPipe() {
		return thicknessOfPipe;
	}

	public void setThicknessOfPipe(String thicknessOfPipe) {
		this.thicknessOfPipe = thicknessOfPipe;
	}

	public String getBracedUnbraced() {
		return bracedUnbraced;
	}

	public void setBracedUnbraced(String bracedUnbraced) {
		this.bracedUnbraced = bracedUnbraced;
	}

	public String getFootingDiameter() {
		return footingDiameter;
	}

	public void setFootingDiameter(String footingDiameter) {
		this.footingDiameter = footingDiameter;
	}

	public String getModuleLayoutOther() {
		return moduleLayoutOther;
	}

	public void setModuleLayoutOther(String moduleLayoutOther) {
		this.moduleLayoutOther = moduleLayoutOther;
	}

	public String getSizeOfPipeOther() {
		return sizeOfPipeOther;
	}

	public void setSizeOfPipeOther(String sizeOfPipeOther) {
		this.sizeOfPipeOther = sizeOfPipeOther;
	}

	public String getThicknessOfPipeOther() {
		return thicknessOfPipeOther;
	}

	public void setThicknessOfPipeOther(String thicknessOfPipeOther) {
		this.thicknessOfPipeOther = thicknessOfPipeOther;
	}

	public String getFootingDiameterOther() {
		return footingDiameterOther;
	}

	public void setFootingDiameterOther(String footingDiameterOther) {
		this.footingDiameterOther = footingDiameterOther;
	}

	public Boolean getOpenTldLibrary() {
		return openTldLibrary;
	}

	public void setOpenTldLibrary(Boolean openTldLibrary) {
		this.openTldLibrary = openTldLibrary;
	}

	public Boolean getTldShortList() {
		return tldShortList;
	}

	public void setTldShortList(Boolean tldShortList) {
		this.tldShortList = tldShortList;
	}

	public String getTldList() {
		return tldList;
	}

	public void setTldList(String tldList) {
		this.tldList = tldList;
	}

	public String getrSheetList() {
		return rSheetList;
	}

	public void setrSheetList(String rSheetList) {
		this.rSheetList = rSheetList;
	}

	public String getBracedUnbracedOther() {
		return bracedUnbracedOther;
	}

	public void setBracedUnbracedOther(String bracedUnbracedOther) {
		this.bracedUnbracedOther = bracedUnbracedOther;
	}

	public Integer getGoogleZoom() {
		return googleZoom;
	}

	public void setGoogleZoom(Integer googleZoom) {
		this.googleZoom = googleZoom;
	}

}
