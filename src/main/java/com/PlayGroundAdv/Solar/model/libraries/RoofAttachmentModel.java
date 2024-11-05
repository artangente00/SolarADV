package com.PlayGroundAdv.Solar.model.libraries;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class RoofAttachmentModel {

	private Long id;
	private String manufacturer;
	private String model;
	private String weight;
	private String integrated;
	private String roofType;
	private String numberOfRoof;
	private String utilizeS100;
	private Boolean isDeleted;
	private Boolean isShown;
	private String owner;
	private String lastUpdate;
	private String idOwner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String allowedRoof;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String compShingle;
	private String flatConcrete;
	private String romanCurved;
	private String standingMetal;
	private String barrelCurve;
	private String rolledComp;
	private String corrugatedMetal;
	private String trapezoidalMetal;
	private String woodorCedar;
	private String clayTile;
	private String fiberCement;
	private String slate;
	private String membraneTpo;
	private String buildUp;
	private String rolledAsphalt;
	private String foam;
	private String liquidApplied;
	private String corrugatedPolyCarb;

	//F.B CR-686
	private AuthentificationEntity firstUpdater;
	private AuthentificationEntity secondUpdater;
	private AuthentificationEntity thirdUpdater;
	private AuthentificationEntity verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	public RoofAttachmentModel(Long id, String compShingle, String flatConcrete, String romanCurved,
			String standingMetal, String barrelCurve, String rolledComp, String corrugatedMetal,
			String trapezoidalMetal, String woodorCedar, String clayTile, String fiberCement, String slate,
			String membraneTpo, String buildUp, String rolledAsphalt, String foam, String liquidApplied,
			String corrugatedPolyCarb) {
		super();
		this.id = id;
		this.compShingle = compShingle;
		this.flatConcrete = flatConcrete;
		this.romanCurved = romanCurved;
		this.standingMetal = standingMetal;
		this.barrelCurve = barrelCurve;
		this.rolledComp = rolledComp;
		this.corrugatedMetal = corrugatedMetal;
		this.trapezoidalMetal = trapezoidalMetal;
		this.woodorCedar = woodorCedar;
		this.clayTile = clayTile;
		this.fiberCement = fiberCement;
		this.slate = slate;
		this.membraneTpo = membraneTpo;
		this.buildUp = buildUp;
		this.rolledAsphalt = rolledAsphalt;
		this.foam = foam;
		this.liquidApplied = liquidApplied;
		this.corrugatedPolyCarb = corrugatedPolyCarb;
	}

	public RoofAttachmentModel() {
		super();
	}

	public RoofAttachmentModel(Long id, String manufacturer, String model) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public RoofAttachmentModel(Long id, String manufacturer, String model, String weight, String integrated,
			String roofType, String numberOfRoof, String utilizeS100, Boolean isDeleted, Boolean isShown, String owner,
			String lastUpdate, String idOwner, Boolean hasCorrectionRequest, String eroneousContent,
			String eroneousContentOther, String eroneousDescription, String allowedRoof,
			String manufacturerMappingValue, String modelMappingValue) {

		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.weight = weight;
		this.integrated = integrated;
		this.roofType = roofType;
		this.numberOfRoof = numberOfRoof;
		this.utilizeS100 = utilizeS100;
		this.isDeleted = isDeleted;
		this.isShown = isShown;
		this.owner = owner;
		this.lastUpdate = lastUpdate;
		this.idOwner = idOwner;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.allowedRoof = allowedRoof;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		byte[] utf8Bytes;
		try {
			utf8Bytes = model.getBytes("UTF8");
			this.model = new String(utf8Bytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			this.model = model;
			e.printStackTrace();
		}
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getIntegrated() {
		return integrated;
	}

	public void setIntegrated(String integrated) {
		this.integrated = integrated;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public String getNumberOfRoof() {
		return numberOfRoof;
	}

	public void setNumberOfRoof(String numberOfRoof) {
		this.numberOfRoof = numberOfRoof;
	}

	public String getUtilizeS100() {
		return utilizeS100;
	}

	public void setUtilizeS100(String utilizeS100) {
		this.utilizeS100 = utilizeS100;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsShown() {
		return isShown;
	}

	public void setIsShown(Boolean isShown) {
		this.isShown = isShown;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}

	public String getEroneousContent() {
		return eroneousContent;
	}

	public void setEroneousContent(String eroneousContent) {
		this.eroneousContent = eroneousContent;
	}

	public String getEroneousContentOther() {
		return eroneousContentOther;
	}

	public void setEroneousContentOther(String eroneousContentOther) {
		this.eroneousContentOther = eroneousContentOther;
	}

	public String getEroneousDescription() {
		return eroneousDescription;
	}

	public void setEroneousDescription(String eroneousDescription) {
		this.eroneousDescription = eroneousDescription;
	}

	public String getAllowedRoof() {
		return allowedRoof;
	}

	public void setAllowedRoof(String allowedRoof) {
		this.allowedRoof = allowedRoof;
	}

	public String getManufacturerMappingValue() {
		return manufacturerMappingValue;
	}

	public void setManufacturerMappingValue(String manufacturerMappingValue) {
		this.manufacturerMappingValue = manufacturerMappingValue;
	}

	public String getModelMappingValue() {
		return modelMappingValue;
	}

	public void setModelMappingValue(String modelMappingValue) {
		this.modelMappingValue = modelMappingValue;
	}

	public String getCompShingle() {
		return compShingle;
	}

	public void setCompShingle(String compShingle) {
		this.compShingle = compShingle;
	}

	public String getFlatConcrete() {
		return flatConcrete;
	}

	public void setFlatConcrete(String flatConcrete) {
		this.flatConcrete = flatConcrete;
	}

	public String getRomanCurved() {
		return romanCurved;
	}

	public void setRomanCurved(String romanCurved) {
		this.romanCurved = romanCurved;
	}

	public String getStandingMetal() {
		return standingMetal;
	}

	public void setStandingMetal(String standingMetal) {
		this.standingMetal = standingMetal;
	}

	public String getBarrelCurve() {
		return barrelCurve;
	}

	public void setBarrelCurve(String barrelCurve) {
		this.barrelCurve = barrelCurve;
	}

	public String getRolledComp() {
		return rolledComp;
	}

	public void setRolledComp(String rolledComp) {
		this.rolledComp = rolledComp;
	}

	public String getCorrugatedMetal() {
		return corrugatedMetal;
	}

	public void setCorrugatedMetal(String corrugatedMetal) {
		this.corrugatedMetal = corrugatedMetal;
	}

	public String getTrapezoidalMetal() {
		return trapezoidalMetal;
	}

	public void setTrapezoidalMetal(String trapezoidalMetal) {
		this.trapezoidalMetal = trapezoidalMetal;
	}

	public String getWoodorCedar() {
		return woodorCedar;
	}

	public void setWoodorCedar(String woodorCedar) {
		this.woodorCedar = woodorCedar;
	}

	public String getClayTile() {
		return clayTile;
	}

	public void setClayTile(String clayTile) {
		this.clayTile = clayTile;
	}

	public String getFiberCement() {
		return fiberCement;
	}

	public void setFiberCement(String fiberCement) {
		this.fiberCement = fiberCement;
	}

	public String getSlate() {
		return slate;
	}

	public void setSlate(String slate) {
		this.slate = slate;
	}

	public String getMembraneTpo() {
		return membraneTpo;
	}

	public void setMembraneTpo(String membraneTpo) {
		this.membraneTpo = membraneTpo;
	}

	public String getBuildUp() {
		return buildUp;
	}

	public void setBuildUp(String buildUp) {
		this.buildUp = buildUp;
	}

	public String getRolledAsphalt() {
		return rolledAsphalt;
	}

	public void setRolledAsphalt(String rolledAsphalt) {
		this.rolledAsphalt = rolledAsphalt;
	}

	public String getFoam() {
		return foam;
	}

	public void setFoam(String foam) {
		this.foam = foam;
	}

	public String getLiquidApplied() {
		return liquidApplied;
	}

	public void setLiquidApplied(String liquidApplied) {
		this.liquidApplied = liquidApplied;
	}

	public String getCorrugatedPolyCarb() {
		return corrugatedPolyCarb;
	}

	public void setCorrugatedPolyCarb(String corrugatedPolyCarb) {
		this.corrugatedPolyCarb = corrugatedPolyCarb;
	}	

	public AuthentificationEntity getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(AuthentificationEntity firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public AuthentificationEntity getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(AuthentificationEntity secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public AuthentificationEntity getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public AuthentificationEntity getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(AuthentificationEntity verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(Date dateVerification) {
		this.dateVerification = dateVerification;
	}

	@Override
	public String toString() {
		return "RoofAttachmentModel [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", weight="
				+ weight + ", integrated=" + integrated + ", roofType=" + roofType + ", numberOfRoof=" + numberOfRoof
				+ ", utilizeS100=" + utilizeS100 + ", isDeleted=" + isDeleted + ", isShown=" + isShown + ", owner="
				+ owner + ", lastUpdate=" + lastUpdate + ", idOwner=" + idOwner + ", hasCorrectionRequest="
				+ hasCorrectionRequest + ", eroneousContent=" + eroneousContent + ", eroneousContentOther="
				+ eroneousContentOther + ", eroneousDescription=" + eroneousDescription + ", allowedRoof=" + allowedRoof
				+ ", manufacturerMappingValue=" + manufacturerMappingValue + ", modelMappingValue=" + modelMappingValue
				+ "]";
	}
}
