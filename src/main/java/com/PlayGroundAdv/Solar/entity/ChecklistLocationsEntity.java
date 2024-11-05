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
@Table(name = "ChecklistLoationsEntity")
public class ChecklistLocationsEntity {
	/*
	 * Permit Engineer Entity
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ChecklistLoations_sequence", sequenceName = "ChecklistLoations_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ChecklistLoations_sequence")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "OWNER")
	private AuthentificationEntity owner;

	@Column(name = "LOCATION_ID")
	private String locationId;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "INSTRUCTION_GUIDE")
	private String instructionGuide;

	@Column(name = "ATTACHEMENT")
	private String attachement;

	@Column(name = "ROOF_MOUNTED")
	private Boolean roofMounted;

	@Column(name = "GROUND_MOUNTED")
	private Boolean groundMounted;

	@Column(name = "POLE_MOUNTED")
	private Boolean poleMounted;

	@Column(name = "PATIO_COVER")
	private Boolean patioCover;

	@Column(name = "OTHER_MOUNTED")
	private Boolean otherMounted;

	@Column(name = "OTHER_MOUNTED_VALUE")
	private String otherMountedValue;

	@Column(name = "AHJ")
	private Boolean ahj;

	@Column(name = "SERVICE_VOLTAGE")
	private Boolean serviceVoltage;

	@Column(name = "ROOFING_MATERIAL")
	private Boolean roofingMaterial;

	@Column(name = "INVERTER_TECHNOLOGY")
	private Boolean inverterTechnology;

	@Column(name = "MORE_THAN_ONE_INVERTER")
	private Boolean moreThanOneInverter;

	@Column(name = "POINT_OF_CONNECTION")
	private Boolean pointOfConnection;

	@Column(name = "PAIRED_WITH_BATTERY_STORAGE")
	private Boolean pairedWithBatteryStorage;

	@Column(name = "AHJ_VALUE")
	private String ahjValue;

	@Column(name = "SERVICE_VOLTAGE_VALUE")
	private String serviceVoltageValue;

	@Column(name = "ROOFING_MATERIAL_VALUE")
	private String roofingMaterialValue;

	@Column(name = "INVERTER_TECHNOLOGY_VALUE")
	private String inverterTechnologyValue;

	@Column(name = "MORE_THAN_ONE_INVERTER_VALUE")
	private Boolean moreThanOneInverterValue;

	@Column(name = "POINT_OF_CONNECTION_VALUE")
	private String pointOfConnectionValue;

	@Column(name = "PAIRED_WITH_BATTERY_STORAGE_VALUE")
	private Boolean pairedWithBatteryStorageValue;

	@Column(name = "IS_SHOWN")
	private Boolean isShown;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	public ChecklistLocationsEntity() {
		super();
	}

	public ChecklistLocationsEntity(Long id, AuthentificationEntity owner, String locationId, String locationName,
			String instructionGuide, String attachement, Boolean roofMounted, Boolean groundMounted,
			Boolean poleMounted, Boolean patioCover, Boolean otherMounted, String otherMountedValue, Boolean ahj,
			Boolean serviceVoltage, Boolean roofingMaterial, Boolean inverterTechnology, Boolean moreThanOneInverter,
			Boolean pointOfConnection, Boolean pairedWithBatteryStorage, String ahjValue, String serviceVoltageValue,
			String roofingMaterialValue, String inverterTechnologyValue, Boolean moreThanOneInverterValue,
			String pointOfConnectionValue, Boolean pairedWithBatteryStorageValue, Boolean isShown, Boolean isDeleted) {
		super();
		this.id = id;
		this.owner = owner;
		this.locationId = locationId;
		this.locationName = locationName;
		this.instructionGuide = instructionGuide;
		this.attachement = attachement;
		this.roofMounted = roofMounted;
		this.groundMounted = groundMounted;
		this.poleMounted = poleMounted;
		this.patioCover = patioCover;
		this.otherMounted = otherMounted;
		this.otherMountedValue = otherMountedValue;
		this.ahj = ahj;
		this.serviceVoltage = serviceVoltage;
		this.roofingMaterial = roofingMaterial;
		this.inverterTechnology = inverterTechnology;
		this.moreThanOneInverter = moreThanOneInverter;
		this.pointOfConnection = pointOfConnection;
		this.pairedWithBatteryStorage = pairedWithBatteryStorage;
		this.ahjValue = ahjValue;
		this.serviceVoltageValue = serviceVoltageValue;
		this.roofingMaterialValue = roofingMaterialValue;
		this.inverterTechnologyValue = inverterTechnologyValue;
		this.moreThanOneInverterValue = moreThanOneInverterValue;
		this.pointOfConnectionValue = pointOfConnectionValue;
		this.pairedWithBatteryStorageValue = pairedWithBatteryStorageValue;
		this.isShown = isShown;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getOwner() {
		return owner;
	}

	public void setOwner(AuthentificationEntity owner) {
		this.owner = owner;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getInstructionGuide() {
		return instructionGuide;
	}

	public void setInstructionGuide(String instructionGuide) {
		this.instructionGuide = instructionGuide;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public Boolean getRoofMounted() {
		return roofMounted;
	}

	public void setRoofMounted(Boolean roofMounted) {
		this.roofMounted = roofMounted;
	}

	public Boolean getGroundMounted() {
		return groundMounted;
	}

	public void setGroundMounted(Boolean groundMounted) {
		this.groundMounted = groundMounted;
	}

	public Boolean getPoleMounted() {
		return poleMounted;
	}

	public void setPoleMounted(Boolean poleMounted) {
		this.poleMounted = poleMounted;
	}

	public Boolean getPatioCover() {
		return patioCover;
	}

	public void setPatioCover(Boolean patioCover) {
		this.patioCover = patioCover;
	}

	public Boolean getOtherMounted() {
		return otherMounted;
	}

	public void setOtherMounted(Boolean otherMounted) {
		this.otherMounted = otherMounted;
	}

	public String getOtherMountedValue() {
		return otherMountedValue;
	}

	public void setOtherMountedValue(String otherMountedValue) {
		this.otherMountedValue = otherMountedValue;
	}

	public Boolean getAhj() {
		return ahj;
	}

	public void setAhj(Boolean ahj) {
		this.ahj = ahj;
	}

	public Boolean getServiceVoltage() {
		return serviceVoltage;
	}

	public void setServiceVoltage(Boolean serviceVoltage) {
		this.serviceVoltage = serviceVoltage;
	}

	public Boolean getRoofingMaterial() {
		return roofingMaterial;
	}

	public void setRoofingMaterial(Boolean roofingMaterial) {
		this.roofingMaterial = roofingMaterial;
	}

	public Boolean getInverterTechnology() {
		return inverterTechnology;
	}

	public void setInverterTechnology(Boolean inverterTechnology) {
		this.inverterTechnology = inverterTechnology;
	}

	public Boolean getMoreThanOneInverter() {
		return moreThanOneInverter;
	}

	public void setMoreThanOneInverter(Boolean moreThanOneInverter) {
		this.moreThanOneInverter = moreThanOneInverter;
	}

	public Boolean getPointOfConnection() {
		return pointOfConnection;
	}

	public void setPointOfConnection(Boolean pointOfConnection) {
		this.pointOfConnection = pointOfConnection;
	}

	public Boolean getPairedWithBatteryStorage() {
		return pairedWithBatteryStorage;
	}

	public void setPairedWithBatteryStorage(Boolean pairedWithBatteryStorage) {
		this.pairedWithBatteryStorage = pairedWithBatteryStorage;
	}

	public String getAhjValue() {
		return ahjValue;
	}

	public void setAhjValue(String ahjValue) {
		this.ahjValue = ahjValue;
	}

	public String getServiceVoltageValue() {
		return serviceVoltageValue;
	}

	public void setServiceVoltageValue(String serviceVoltageValue) {
		this.serviceVoltageValue = serviceVoltageValue;
	}

	public String getRoofingMaterialValue() {
		return roofingMaterialValue;
	}

	public void setRoofingMaterialValue(String roofingMaterialValue) {
		this.roofingMaterialValue = roofingMaterialValue;
	}

	public String getInverterTechnologyValue() {
		return inverterTechnologyValue;
	}

	public void setInverterTechnologyValue(String inverterTechnologyValue) {
		this.inverterTechnologyValue = inverterTechnologyValue;
	}

	public Boolean getMoreThanOneInverterValue() {
		return moreThanOneInverterValue;
	}

	public void setMoreThanOneInverterValue(Boolean moreThanOneInverterValue) {
		this.moreThanOneInverterValue = moreThanOneInverterValue;
	}

	public String getPointOfConnectionValue() {
		return pointOfConnectionValue;
	}

	public void setPointOfConnectionValue(String pointOfConnectionValue) {
		this.pointOfConnectionValue = pointOfConnectionValue;
	}

	public Boolean getPairedWithBatteryStorageValue() {
		return pairedWithBatteryStorageValue;
	}

	public void setPairedWithBatteryStorageValue(Boolean pairedWithBatteryStorageValue) {
		this.pairedWithBatteryStorageValue = pairedWithBatteryStorageValue;
	}

	public Boolean getIsShown() {
		return isShown;
	}

	public void setIsShown(Boolean isShown) {
		this.isShown = isShown;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "ChecklistLoationsEntity [id=" + id + ", owner=" + owner + ", locationId=" + locationId
				+ ", locationName=" + locationName + ", instructionGuide=" + instructionGuide + ", attachement="
				+ attachement + ", roofMounted=" + roofMounted + ", groundMounted=" + groundMounted + ", poleMounted="
				+ poleMounted + ", patioCover=" + patioCover + ", otherMounted=" + otherMounted + ", otherMountedValue="
				+ otherMountedValue + ", ahj=" + ahj + ", serviceVoltage=" + serviceVoltage + ", roofingMaterial="
				+ roofingMaterial + ", inverterTechnology=" + inverterTechnology + ", moreThanOneInverter="
				+ moreThanOneInverter + ", pointOfConnection=" + pointOfConnection + ", pairedWithBatteryStorage="
				+ pairedWithBatteryStorage + ", ahjValue=" + ahjValue + ", serviceVoltageValue=" + serviceVoltageValue
				+ ", roofingMaterialValue=" + roofingMaterialValue + ", inverterTechnologyValue="
				+ inverterTechnologyValue + ", moreThanOneInverterValue=" + moreThanOneInverterValue
				+ ", pointOfConnectionValue=" + pointOfConnectionValue + ", pairedWithBatteryStorageValue="
				+ pairedWithBatteryStorageValue + "]";
	}

}
