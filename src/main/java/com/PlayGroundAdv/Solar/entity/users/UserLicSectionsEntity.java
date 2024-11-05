package com.PlayGroundAdv.Solar.entity.users;

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

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: UserLicSectionEntity
 *
 */
@Getter
@Setter
@Entity
@Table(name="User_Lic_Section_Entity")
public class UserLicSectionsEntity implements Serializable {

	
private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="hibernate_sequence17", sequenceName = "hibernate_sequence17", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence17")  
	private Long id;

	
	@JoinColumn(name = "ID_AUTH")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;
	
	@Column(name="IS_IN_US_OR_TERRITORIES")
	private Boolean isInUSorTerritories;
	
	@Column(name="CONTRACTOR_LICENCE_STATE")
	private String contractorLicenceState;
	
	@Column(name="LICENSE_NUMBER")
	private String licenseNumber;
	
	@Column(name="LIC_TYPE_CODE")
	private String[] licTypeCode;
	
	@Column(name="FIRST_LIC_TYPE_CODE_OTHER")
	private String firstLicTypeCodeOther;
	
	@Column(name="SECOND_LIC_TYPE_CODE_OTHER")
	private String secondLicTypeCodeOther;
	
	@Column(name="THIRD_LIC_TYPE_CODE_OTHER")
	private String thirdLicTypeCodeOther;
	
	@Column(name="LIC_TYPE")
	private String[] licType;
	
	@Column(name="LIC_TYPE_OTHER")
	private String licTypeOther;
	
	@Column(name="LICENSE_EXPIRATION")
	private String licenseExpiration;
	
	@Column(name="QUALIFYING_INDIVIDUAL")
	private String qualifyingIndividual;
	
	@Column(name="QUALIFYING_INDIVIDUAL_OTHER")
	private String qualifyingIndividualOther;
	
	@Column
	private String additionalEmail1;
	
	@Column
	private String additionalEmail2;
	
	@Column
	private String additionalEmail3;
	
	@Column
	private String additionalEmail4;
}
