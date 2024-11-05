package com.PlayGroundAdv.Solar.entity.libraries;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Generator {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String manufacturer;
	
	@Column
	private String model;
	
	@Column
	private String manufacturerMappingValue;
	
	@Column
	private String modelMappingValue;
	
	@Column
	private Integer ratedPower;
	
	@Column
	private Integer maxContinuousOutput;
	
	@Type(type = "list-array")
    @Column(columnDefinition = "text[]")
	private List<String> ratedVoltage;
	
	@Type(type = "list-array")
    @Column(columnDefinition = "text[]")
	private List<String> fuelType;
	
	@Column
	private Boolean integratedAutoTransferSwitch;
	
	@Column
	private String emissionsComplianceRating;
	
	@Column
	private Boolean hasCorrectionRequest = false;
	
	@Column
	private String eroneousContent;
	
	@Column
	private String eroneousContentOther;
	
	@Column
	private String eroneousDescription;
	
	@Column
	private Boolean deleted = false;
	
	@Column
	private Boolean hasSuperUserEdit;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedDate;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@JoinColumn
	@ManyToOne
	private AuthentificationEntity addedBy;
	
	@JoinColumn
	@ManyToOne
	private AuthentificationEntity deletedBy;
	
	@JoinColumn
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idGenerator")
	private Set<GeneratorFavorite> favorite = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idGenerator")
	private Set<PermitEnergyBatterySystem> projects = new HashSet<>();

	@JoinColumn(name = "FIRST_UPDATER")
	@ManyToOne
	private AuthentificationEntity firstUpdater;
	
	@JoinColumn(name = "SECOND_UPDATER")
	@ManyToOne
	private AuthentificationEntity secondUpdater;
	
	@JoinColumn(name = "THIRD_UPDATER")
	@ManyToOne
	private AuthentificationEntity thirdUpdater;
	
	@JoinColumn(name = "VERIFIED_BY")
	@ManyToOne
	private AuthentificationEntity verifiedBy;
	
	@Column(name="IS_VERIFIED")
	private Boolean isVerified;
	
	@Column(name="DATE_VERIFICATION")
	private Date dateVerification;	
	

	public Generator(String manufacturer, String model, String manufacturerMappingValue, String modelMappingValue,
			Integer ratedPower, Integer maxContinuousOutput, List<String> ratedVoltage, List<String> fuelType,
			Boolean integratedAutoTransferSwitch, String emissionsComplianceRating, AuthentificationEntity addedBy) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.ratedPower = ratedPower;
		this.maxContinuousOutput = maxContinuousOutput;
		this.ratedVoltage = ratedVoltage;
		this.fuelType = fuelType;
		this.integratedAutoTransferSwitch = integratedAutoTransferSwitch;
		this.emissionsComplianceRating = emissionsComplianceRating;
		this.addedBy = addedBy;
	}

}
