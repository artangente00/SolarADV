package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * @author Arij
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "RsheetsLibraryEntity")
public class RsheetsLibraryEntity {

	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="RsheetsLibrary_sequence",
			           sequenceName="RsheetsLibrary_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RsheetsLibrary_sequence")  
	private Long id;
	
	@Column(name="pdf_Name")
	private String pdfName;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;
	
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;
	
	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	@Column(name = "DELETED_ON")
	private Date deletedOn;
	
	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="COMPONENT_TYPE")
	private String componentType;
	
	@Column(name="GROUND_RAIL_RACKING")
	private Boolean groundRailRacking;
	
	@Column(name="SNOW_LOAD")
	private String snowLoad;
	
	@Column(name="SNOW_LOAD_OTHER")
	private String snowLoadOther;
	
	@Column(name="WIND_SPEED")
	private String windSpeed;
	
	@Column(name="WIND_SPEED_OTHER")
	private String windSpeedOther;
	
	@Column(name="PIPE_SIZE")
	private String pipeSize;
	
	@Column(name="PIPE_SIZE_OTHER")
	private String pipeSizeOther;
	
	@Column(name="FOOTING_DIAMETER")
	private String footingDiameter;
	
	@Column(name="FOOTING_DIAMETER_OTHER")
	private String footingDiameterOther;
	
	@Column(name="BRACED_UNBRACED")
	private String bracedOrUnbraced;
	
	@Column(name="MODULE_LAYOUT")
	private String moduleLayout;
	
	@Column(name="MODULE_LAYOUT_OTHER")
	private String moduleLayoutOther;
	
	@Column(name="TILT_RANGE")
	private String tiltRange;
	
	@Column(name="THICKNESS_PIPE")
	private String thicknessPipe;
	
	@Column(name="THICKNESS_PIPE_OTHER")
	private String thicknessPipeOther;
	
	@Column(name="EXPOSURE_CATEGORY")
	private String exposureCategory;
	
	@Column(name="EXPOSURE_CATEGORY_OTHER")
	private String exposureCategoryOther;
	
	@Column(name="MANUFACTURER")
	private String manufacturer;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="BRACED_UNBRACED_OTHER")
	private String bracedOrUnbracedOther;
	
	//A.B 08/02/2021 CR-3634-MOD-001
	@Column
	private String asceStandard;
}
