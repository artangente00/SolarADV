package com.PlayGroundAdv.Solar.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RailRacking")
@RequiredArgsConstructor

public class RailRacking {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Getter
	private Long id;
	
	@Column(name="MANUFACTURER")
	@Getter
	@Setter
	private String manufacturer;
	
	@Column(name="MODEL")
	@Getter
	@Setter
	private String model;
	
	@Column(name="WEIGHT")
	@Getter
	@Setter
	private String weight;
	
	@Column(name="TYPE_OF_SYSTEM")
	@Getter
	@Setter
	private String typeOfSystem;
	
	@Column(name="LAST_UPDATE")
	@Getter
	@Setter
	private String lastUpdate;
	
	@Column(name="IS_DELETED")
	@Getter
	@Setter
	private Boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	@Getter
	@Setter
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	@Getter
	@Setter
	private Date addDate;
	
	@JoinColumn(name = "ID_OWNER")
	@Getter
	@Setter
	@ManyToOne
	private AuthentificationEntity idOwner;
	
	@Column(name="ERONEOUS_CONTENT")
	@Getter
	@Setter
	private String eroneousContent;
	
	@Column(name="ERONEOUS_CONTENT_OTHER")
	@Getter
	@Setter
	private String eroneousContentOther;
	
	@Column(name="ERONEOUS_DESCRIPTION")
	@Getter
	@Setter
	private String eroneousDescription;
	
	@Column(name="HAS_CORRECTION_REQUEST")
	@Getter
	@Setter
	private Boolean hasCorrectionRequest;
	
	@Column(name="MANUFACTURER_MAPPING_VALUE")
	@Getter
	@Setter
	private String manufacturerMappingValue;
	
	@Column(name="MODEL_MAPPING_VALUE")
	@Getter
	@Setter
	private String modelMappingValue;
	
	@OneToMany(targetEntity=MountingTypeEntity.class, mappedBy="idRail",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	@Getter
	@Setter
	private List<MountingTypeEntity> mountType = new ArrayList<>();
	
	@ManyToOne()
	@JoinColumn(name="PV_RAIL_TYPE")
	@Getter
	@Setter
	private RailRackingOptionsEntity pvRailType;
	
	@ManyToOne()
	@JoinColumn(name="PV_RAIL_SPLICE_TYPE")
	@Getter
	@Setter
	private RailRackingOptionsEntity pvRailSpliceType;
	
	@ManyToOne()
	@JoinColumn(name="MID_CLAMP")
	@Getter
	@Setter
	private RailRackingOptionsEntity midClamp;
	
	@ManyToOne()
	@JoinColumn(name="END_CLAMP")
	@Getter
	@Setter
	private RailRackingOptionsEntity endClamp;

	@Column(name="INTEGRATED_STANCHION")
	@Getter
	@Setter
	private Boolean integratedStanchion;
	
	@Column(name="STANCHION_MFG")
	@Getter
	@Setter
	private String stanchionMfg;
	
	@Column(name="STANCHION_MFG_MAPPING_VALUE")
	@Getter
	@Setter
	private String stanchionMfgMappingValue;
	
	@Column(name="STANCHION_MODEL")
	@Getter
	@Setter
	private String stanchionModel;
	
	@Column(name="STANCHION_MODEL_MAPPING_VALUE")
	@Getter
	@Setter
	private String stanchionModelMappingValue;
	
	@Column(name="INTEGRATED_FLASHING")
	@Getter
	@Setter
	private Boolean integratedFlashing;
	
	@OneToMany(mappedBy = "railRacking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Getter
	@Setter
	@Fetch(FetchMode.JOIN)
	private Set<RackingAllowedRoofMaterial> rackingAllowedRoofMaterial = new HashSet<>();
	
	@JoinColumn(name = "FIRST_UPDATER")
	@ManyToOne
	@Getter
	@Setter
	private AuthentificationEntity firstUpdater;
	
	@JoinColumn(name = "SECOND_UPDATER")
	@ManyToOne
	@Getter
	@Setter
	private AuthentificationEntity secondUpdater;
	
	@JoinColumn(name = "THIRD_UPDATER")
	@ManyToOne
	@Getter
	@Setter
	private AuthentificationEntity thirdUpdater;
	
	@JoinColumn(name = "VERIFIED_BY")
	@ManyToOne
	@Getter
	@Setter
	private AuthentificationEntity verifiedBy;
	
	@Column(name="IS_VERIFIED")
	@Getter
	@Setter
	private Boolean isVerified;
	
	@Column(name="DATE_VERIFICATION")
	@Getter
	@Setter
	private Date dateVerification;	



	
}
