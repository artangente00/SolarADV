package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PermitAdditionalInfoEntityResult {

	private String formatSize;
	private Boolean batteryStorage;
	private Integer quantityBatteries;
	private Boolean tiltLegs;

	// CR_003
	private Boolean informationCovered;
	private Boolean requiredElectricalStamp;

	private String uploadComments;
	private String battery;
	private String tiltLegsMod;
	private String gridTiedOrStandalone;

	private Boolean existSolarSystem;
	private String existpvmodule;
	private Integer existmoduleqty;
	private String existinvertermodel;
	private Integer existinverterqty;
	private String existinvertermodelTwo;
	private Integer existinverterqtyTwo;
	private String existmicromodel;
	private Integer existmicroqty;
	private String existacdisconnect;
	private String existpvmeter;
	private String acdpvmorientation;
	private String pointofconnection;
	private String pocwillbeat;
	private String sizebackfed;
	private String otherPointConnection;
	private String otherpocwillbeat;
	private String combiningpvin;
	private String existingInverterTech;

	public PermitAdditionalInfoEntityResult(String formatSize, Boolean batteryStorage, Integer quantityBatteries,
			Boolean tiltLegs, Boolean informationCovered,
			String uploadComments, Boolean requiredElectricalStamp, String battery, String tiltLegsMod,
			String gridTiedOrStandalone, Boolean existSolarSystem, String existpvmodule, Integer existmoduleqty,
			String existinvertermodel, Integer existinverterqty, String existinvertermodelTwo,
			Integer existinverterqtyTwo, String existmicromodel, Integer existmicroqty, String existacdisconnect,
			String existpvmeter, String acdpvmorientation, String pointofconnection, String pocwillbeat,
			String sizebackfed, String otherPointConnection, String otherpocwillbeat, String combiningpvin,
			String existingInverterTech) {

		super();
		this.formatSize = formatSize;
		this.batteryStorage = batteryStorage;
		this.quantityBatteries = quantityBatteries;
		this.tiltLegs = tiltLegs;
		this.informationCovered = informationCovered;
		this.requiredElectricalStamp = requiredElectricalStamp;
		this.uploadComments = uploadComments;
		this.battery = battery;
		this.tiltLegsMod = tiltLegsMod;
		this.gridTiedOrStandalone = gridTiedOrStandalone;
		this.existSolarSystem = existSolarSystem;
		this.existpvmodule = existpvmodule;
		this.existmoduleqty = existmoduleqty;
		this.existinvertermodel = existinvertermodel;
		this.existinverterqty = existinverterqty;
		this.existinvertermodelTwo = existinvertermodelTwo;
		this.existinverterqtyTwo = existinverterqtyTwo;
		this.existmicromodel = existmicromodel;
		this.existmicroqty = existmicroqty;
		this.existacdisconnect = existacdisconnect;
		this.existpvmeter = existpvmeter;
		this.acdpvmorientation = acdpvmorientation;
		this.pointofconnection = pointofconnection;
		this.pocwillbeat = pocwillbeat;
		this.sizebackfed = sizebackfed;
		this.otherPointConnection = otherPointConnection;
		this.otherpocwillbeat = otherpocwillbeat;
		this.combiningpvin = combiningpvin;
		this.existingInverterTech = existingInverterTech;
	}
}
