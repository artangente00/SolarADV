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

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "PermitAdditionalInfoEntity")
public class PermitAdditionalInfoEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="hibernate_sequence3", sequenceName = "hibernate_sequence3", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence3")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="FORMAT_SIZE")
	private String formatSize;
	
	@Column(name="BATTERY_STORAGE")
	private Boolean batteryStorage;
	
	@Column(name="TILT_LEGS")
	private Boolean tiltLegs;

	@Column(name="BATTERY_MANUFACTURER")
	private String batteryManufacturer;
	
	@Column(name="BATTERY_MODEL")
	private String batteryModel;
	
	@Column(name="QUANTITY_BATTERY")
	private Integer quantityBatteries;
	
	@Column(name="uploadComments")
	private String uploadComments;
	
	// CR_003
	@Column(name="INFORMATION_COVERED")
	private Boolean informationCovered;
	
	@Column(name="REQUIRED_ELECTRICAL_STAMP")
	private Boolean requiredElectricalStamp;
	
	@Column(name="BATTERY")
	private String battery; 

	@Column(name="TILT_LEGS_MOD")
	private String tiltLegsMod;

	@Column(name="GRID_TIED_OR_STANDALONE")
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
}
