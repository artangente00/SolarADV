package com.PlayGroundAdv.Solar.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SsheetRackingMappingEntity")
public class SsheetRackingMappingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "ROOF_TYPE")
	private String roofType;

	@Column(name = "ROOF_TYPE_OTHER")
	private String roofTypeOther;

	@JoinColumn(name = "ROOF_MATERIAL_TYPE")
	@ManyToOne
	private RoofMaterialType roofMaterialTyp;
	
	@OneToMany(mappedBy = "detailRackingId", cascade = CascadeType.ALL)
	private List<DetailRaickingRoofMaterial> roofMaterialTypes = new ArrayList<>();
	
	@Column(name = "RACKING_MANUFACTURER")
	private String rackingManufacturer;

	@Column(name = "RACKING_MODEL")
	private String rackingModel;

	@Column(name = "ROOF_MANUFACTURER")
	private String roofManufacturer;

	@Column(name = "ROOF_MODEL")
	private String roofModel;

	@Column(name = "RACKING_TYPE", columnDefinition = "varchar(255) default 'any'")
	private String mountingType;
	
	@Column(name = "FLASHING")
	private Boolean flashing;
	
	@Column(name = "FLASHING_MANUFACTURER")
	private String flashingManufacturer;
	
	@Column(name = "STATE" , columnDefinition = "varchar(255) default 'any'")
	private String state;

	@Column(name = "SHEET_NUMBER")
	private String sheetNumber;

	@Column(name = "QUAD_NUMBER")
	private String quadNumber;

	@Column(name = "DETAILS_HEADING")
	private String detailsHeading;

	@Column(name = "PDF_NAME")
	private String pdfname;

	@Column(name = "AHJ")
	private String ahj;

	@Column(name = "UTILITY_COMPANY")
	private String utilityCompany;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;

	@Column(name = "DELETED_ON")
	private Date deletedOn;

	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	@JoinColumn(name = "S_SHEET_FILE")
	@ManyToOne
	private SsheetLibraryEntity sSheetFile;
	
	@Column
	private String stanchionType;

	public void addRoofType(RoofMaterialType b) {
		DetailRaickingRoofMaterial bat = new DetailRaickingRoofMaterial(this, b);
		roofMaterialTypes.add(bat);
	}



}
