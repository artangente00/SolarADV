package com.PlayGroundAdv.Solar.model;

public class PermitAdvEntityResult {

	private String windSpeed;
	private String snowLoad;
	private String googleImage;
	private String mapImage;
	private String pVRailQteString;
	private String pVRailLength;
	private String stanchionQteString;
	private String stanchionLength;
	private String spliceQteString;
	private String spliceLength;
	private String s200QteString;
	private String s200Length;
	private String pv1;
	private String customersServiceAgreementIDNumber;
	private String customersRateSchedule;
	private String engineeringFirm;
	private String customersAccountNumber;
	private String customerName;
	// formate String to integer
	private String pVRailQte;
	private String stanchionQte;
	private String spliceQte;
	private String s200Qte;
	// CR-1231- Add new field Other wind speed
	private String windSpeedOther;
	// CR-1231- Add new field Other snow load
	private String snowLoadOther;

	private String uploadCommentsGoogle;
	private String uploadCommentsNearMap;
	// CR-1181- Update Edit Project Inputs
	private String moduleLayout;
	private String sizeOfPipe;
	private String thicknessOfPipe;
	private String bracedUnbraced;
	private String footingDiameter;
	private String moduleLayoutOther;
	private String sizeOfPipeOther;
	private String thicknessOfPipeOther;
	private String footingDiameterOther;

	private Boolean openTldLibrary;
	private Boolean tldShortList;
	private String tldList;
	private String rSheetList;
	private String bracedUnbracedOther;
	private Integer googleZoom;

	public PermitAdvEntityResult() {
		super();
	}

	public PermitAdvEntityResult(String windSpeed, String snowLoad, String googleImage, String mapImage,
			String pVRailQte, String pVRailLength, String stanchionQte, String stanchionLength, String spliceQte,

			String spliceLength, String s200Qte, String s200Length, String pv1,
			String customersServiceAgreementIDNumber, String customersRateSchedule, String engineeringFirm,
			String customersAccountNumber, String customerName, String windSpeedOther, String snowLoadOther,
			String uploadCommentsGoogle, String uploadCommentsNearMap, String moduleLayout, String sizeOfPipe,
			String thicknessOfPipe, String bracedUnbraced, String footingDiameter, String moduleLayoutOther,
			String sizeOfPipeOther, String thicknessOfPipeOther, String footingDiameterOther, Boolean openTldLibrary,
			Boolean tldShortList, String tldList, String rSheetList, String bracedUnbracedOther,Integer googleZoom) {

		super();
		this.windSpeed = windSpeed;
		this.snowLoad = snowLoad;
		this.googleImage = googleImage;
		this.mapImage = mapImage;
		this.pVRailQte = pVRailQte;
		this.pVRailLength = pVRailLength;
		this.stanchionQte = stanchionQte;
		this.stanchionLength = stanchionLength;
		this.spliceQte = spliceQte;
		this.spliceLength = spliceLength;
		this.s200Qte = s200Qte;
		this.s200Length = s200Length;
		this.pv1 = pv1;
		this.customersServiceAgreementIDNumber = customersServiceAgreementIDNumber;
		this.customersRateSchedule = customersRateSchedule;
		this.engineeringFirm = engineeringFirm;
		this.customersAccountNumber = customersAccountNumber;
		this.customerName = customerName;
		this.windSpeedOther = windSpeedOther;
		this.snowLoadOther = snowLoadOther;
		this.uploadCommentsGoogle = uploadCommentsGoogle;
		this.uploadCommentsNearMap = uploadCommentsNearMap;
		this.moduleLayout = moduleLayout;
		this.sizeOfPipe = sizeOfPipe;
		this.thicknessOfPipe = thicknessOfPipe;
		this.bracedUnbraced = bracedUnbraced;
		this.footingDiameter = footingDiameter;
		this.moduleLayoutOther = moduleLayoutOther;
		this.sizeOfPipeOther = sizeOfPipeOther;
		this.thicknessOfPipeOther = thicknessOfPipeOther;
		this.footingDiameterOther = footingDiameterOther;

		this.openTldLibrary = openTldLibrary;
		this.tldShortList = tldShortList;
		this.tldList = tldList;
		this.rSheetList = rSheetList;
		this.bracedUnbracedOther = bracedUnbracedOther;
		this.googleZoom = googleZoom;
	}

	public String getpVRailQteString() {
		return pVRailQteString;
	}

	public void setpVRailQteString(String pVRailQteString) {
		this.pVRailQteString = pVRailQteString;
	}

	public String getStanchionQteString() {
		return stanchionQteString;
	}

	public void setStanchionQteString(String stanchionQteString) {
		this.stanchionQteString = stanchionQteString;
	}

	public String getSpliceQteString() {
		return spliceQteString;
	}

	public void setSpliceQteString(String spliceQteString) {
		this.spliceQteString = spliceQteString;
	}

	public String getS200QteString() {
		return s200QteString;
	}

	public void setS200QteString(String s200QteString) {
		this.s200QteString = s200QteString;
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

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getSnowLoad() {
		return snowLoad;
	}

	public void setSnowLoad(String snowLoad) {
		this.snowLoad = snowLoad;
	}

	public String getGoogleImage() {
		return googleImage;
	}

	public void setGoogleImage(String googleImage) {
		this.googleImage = googleImage;
	}

	public String getMapImage() {
		return mapImage;
	}

	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}

	public String getpVRailQte() {
		return pVRailQteString;
	}

	public void setpVRailQte(String pVRailQte) {
		this.pVRailQteString = pVRailQte;
	}

	public String getpVRailLength() {
		return pVRailLength;
	}

	public void setpVRailLength(String pVRailLength) {
		this.pVRailLength = pVRailLength;
	}

	public String getStanchionQte() {
		return stanchionQteString;
	}

	public void setStanchionQte(String stanchionQte) {
		this.stanchionQteString = stanchionQte;
	}

	public String getStanchionLength() {
		return stanchionLength;
	}

	public void setStanchionLength(String stanchionLength) {
		this.stanchionLength = stanchionLength;
	}

	public String getSpliceQte() {
		return spliceQteString;
	}

	public void setSpliceQte(String spliceQte) {
		this.spliceQteString = spliceQte;
	}

	public String getSpliceLength() {
		return spliceLength;
	}

	public void setSpliceLength(String spliceLength) {
		this.spliceLength = spliceLength;
	}

	public String getS200Qte() {
		return s200QteString;
	}

	public void setS200Qte(String s200Qte) {
		this.s200QteString = s200Qte;
	}

	public String getS200Length() {
		return s200Length;
	}

	public void setS200Length(String s200Length) {
		this.s200Length = s200Length;
	}

	public String getPv1() {
		return pv1;
	}

	public void setPv1(String pv1) {
		this.pv1 = pv1;
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
