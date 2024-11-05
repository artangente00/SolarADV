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

/**
 * @author pc
 *
 */
@Entity
@Table(name = "PermitEngineerEntity")
public class PermitEngineerEntity {

	
	/**
	 *  Permit Engineer Entity
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="hibernate_sequence10", sequenceName = "hibernate_sequence10", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence10")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="APPLICABL_ENGINEERING")
	private String applicablEngineering;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="MOBILE")
	private String mobile;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="LICENCE_NUMBER")
	private String licenceNumber;
	
	@Column(name="LICENCE_TYPE")
	private String licenceType;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="CODE_POSTALE")
	private String codePostale;
	
	@Column(name="ENGINEERED_BY")
	private String engineeredBy;
	
	@Column(name="DETERMINE_MODIFICATION")
	private Boolean determineModification;
	
	
	@Column(name="IS_SHINGLES")
	private Boolean isShingles;
	
	@Column(name="INDICATE_LAYERS")
	private String indicateLayers;
	
	
	@Column(name="MPPT_TRACHERS")
	private Boolean mpptTrachers;
	
	@Column(name="NUMBER_MPPT_TRACHER")
	private String NumberMpptTrachers;
	
	@Column(name="NUMBER_STRING_FIRST_MPPT_TRACHER")
	private String NumberStringFirstMpptTrachers;
	
	
	@Column(name="NUMBER_STRING_SECOND_MPPT_TRACHER")
	private String NumberStringSecondMpptTrachers;
	
	@Column(name="NUMBER_MODULE_STRING_FIRST_MPPT_TRACHER")
	private String NumberModuleStringFirstMpptTrachers;
	
	@Column(name="NUMBER_MODULE_STRING_SECOND_MPPT_TRACHER")
	private String NumberModuleStringSecondMpptTrachers;
	
	@Column(name="IS_TRANSFORMLESS")
	private Boolean isTransformless;
	
	@Column(name="NUMBER_INPUT_TRANSFORMLESS")
	private String numberInputTransformless;
	
	@Column(name="IS_COMBINER")
	private Boolean isCombiner;
	
	@Column(name="NUMBER_INPUT_COMBINER")
	private String numberInputCombiner;
	
	@Column(name="OVERHANG_AREA")
	private String overhangArea;
	
	@Column(name="ROOF_PITCH")
	private String roofPitch;
	
	@Column(name="ADRESS_ING")
	private String adressIng;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the applicableEngineering
	 */
	public String getApplicablEngineering() {
		return applicablEngineering;
	}

	/**
	 * @param applicableEngineering the applicableEngineering to set
	 */
	public void setApplicablEngineering(String applicablEngineering) {
		this.applicablEngineering = applicablEngineering;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the licenceNumber
	 */
	public String getLicenceNumber() {
		return licenceNumber;
	}

	/**
	 * @param licenceNumber the licenceNumber to set
	 */
	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	/**
	 * @return the licenceType
	 */
	public String getLicenceType() {
		return licenceType;
	}

	/**
	 * @param licenceType the licenceType to set
	 */
	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the codePostale
	 */
	public String getCodePostale() {
		return codePostale;
	}

	/**
	 * @param codePostale the codePostale to set
	 */
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	/**
	 * @return the engineeredBy
	 */
	public String getEngineeredBy() {
		return engineeredBy;
	}

	/**
	 * @param engineeredBy the engineeredBy to set
	 */
	public void setEngineeredBy(String engineeredBy) {
		this.engineeredBy = engineeredBy;
	}

	/**
	 * @return the determineModification
	 */
	public Boolean getDetermineModification() {
		return determineModification;
	}

	/**
	 * @param determineModification the determineModification to set
	 */
	public void setDetermineModification(Boolean determineModification) {
		this.determineModification = determineModification;
	}

	/**
	 * @return the isShingles
	 */
	public Boolean getIsShingles() {
		return isShingles;
	}

	/**
	 * @param isShingles the isShingles to set
	 */
	public void setIsShingles(Boolean isShingles) {
		this.isShingles = isShingles;
	}

	/**
	 * @return the mpptTrachers
	 */
	public Boolean getMpptTrachers() {
		return mpptTrachers;
	}

	/**
	 * @param mpptTrachers the mpptTrachers to set
	 */
	public void setMpptTrachers(Boolean mpptTrachers) {
		this.mpptTrachers = mpptTrachers;
	}

	/**
	 * @return the numberMpptTrachers
	 */
	public String getNumberMpptTrachers() {
		return NumberMpptTrachers;
	}

	/**
	 * @param numberMpptTrachers the numberMpptTrachers to set
	 */
	public void setNumberMpptTrachers(String numberMpptTrachers) {
		NumberMpptTrachers = numberMpptTrachers;
	}

	/**
	 * @return the numberStringFirstMpptTrachers
	 */
	public String getNumberStringFirstMpptTrachers() {
		return NumberStringFirstMpptTrachers;
	}

	/**
	 * @param numberStringFirstMpptTrachers the numberStringFirstMpptTrachers to set
	 */
	public void setNumberStringFirstMpptTrachers(String numberStringFirstMpptTrachers) {
		NumberStringFirstMpptTrachers = numberStringFirstMpptTrachers;
	}

	/**
	 * @return the numberStringSecondMpptTrachers
	 */
	public String getNumberStringSecondMpptTrachers() {
		return NumberStringSecondMpptTrachers;
	}

	/**
	 * @param numberStringSecondMpptTrachers the numberStringSecondMpptTrachers to set
	 */
	public void setNumberStringSecondMpptTrachers(String numberStringSecondMpptTrachers) {
		NumberStringSecondMpptTrachers = numberStringSecondMpptTrachers;
	}

	/**
	 * @return the numberModuleStringFirstMpptTrachers
	 */
	public String getNumberModuleStringFirstMpptTrachers() {
		return NumberModuleStringFirstMpptTrachers;
	}

	/**
	 * @param numberModuleStringFirstMpptTrachers the numberModuleStringFirstMpptTrachers to set
	 */
	public void setNumberModuleStringFirstMpptTrachers(String numberModuleStringFirstMpptTrachers) {
		NumberModuleStringFirstMpptTrachers = numberModuleStringFirstMpptTrachers;
	}

	/**
	 * @return the numberModuleStringSecondMpptTrachers
	 */
	public String getNumberModuleStringSecondMpptTrachers() {
		return NumberModuleStringSecondMpptTrachers;
	}

	/**
	 * @param numberModuleStringSecondMpptTrachers the numberModuleStringSecondMpptTrachers to set
	 */
	public void setNumberModuleStringSecondMpptTrachers(String numberModuleStringSecondMpptTrachers) {
		NumberModuleStringSecondMpptTrachers = numberModuleStringSecondMpptTrachers;
	}

	/**
	 * @return the isTransformless
	 */
	public Boolean getIsTransformless() {
		return isTransformless;
	}

	/**
	 * @param isTransformless the isTransformless to set
	 */
	public void setIsTransformless(Boolean isTransformless) {
		this.isTransformless = isTransformless;
	}

	/**
	 * @return the numberInputTransformless
	 */
	public String getNumberInputTransformless() {
		return numberInputTransformless;
	}

	/**
	 * @param numberInputTransformless the numberInputTransformless to set
	 */
	public void setNumberInputTransformless(String numberInputTransformless) {
		this.numberInputTransformless = numberInputTransformless;
	}

	/**
	 * @return the isCombiner
	 */
	public Boolean getIsCombiner() {
		return isCombiner;
	}

	/**
	 * @param isCombiner the isCombiner to set
	 */
	public void setIsCombiner(Boolean isCombiner) {
		this.isCombiner = isCombiner;
	}

	/**
	 * @return the numberInputCombiner
	 */
	public String getNumberInputCombiner() {
		return numberInputCombiner;
	}

	/**
	 * @param numberInputCombiner the numberInputCombiner to set
	 */
	public void setNumberInputCombiner(String numberInputCombiner) {
		this.numberInputCombiner = numberInputCombiner;
	}

	/**
	 * @return the overhangArea
	 */
	public String getOverhangArea() {
		return overhangArea;
	}

	/**
	 * @param overhangArea the overhangArea to set
	 */
	public void setOverhangArea(String overhangArea) {
		this.overhangArea = overhangArea;
	}

	/**
	 * @return the roofPitch
	 */
	public String getRoofPitch() {
		return roofPitch;
	}

	/**
	 * @param roofPitch the roofPitch to set
	 */
	public void setRoofPitch(String roofPitch) {
		this.roofPitch = roofPitch;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @return the indicateLayers
	 */
	public String getIndicateLayers() {
		return indicateLayers;
	}

	/**
	 * @param indicateLayers the indicateLayers to set
	 */
	public void setIndicateLayers(String indicateLayers) {
		this.indicateLayers = indicateLayers;
	}

	/**
	 * @return the adressIng
	 */
	public String getAdressIng() {
		return adressIng;
	}

	/**
	 * @param adressIng the adressIng to set
	 */
	public void setAdressIng(String adressIng) {
		this.adressIng = adressIng;
	}

	@Override
	public String toString() {
		return "PermitEngineerEntity [id=" + id + ", permitEntity=" + permitEntity + ", applicablEngineering="
				+ applicablEngineering + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", phone="
				+ phone + ", licenceNumber=" + licenceNumber + ", licenceType=" + licenceType + ", city=" + city
				+ ", state=" + state + ", codePostale=" + codePostale + ", engineeredBy=" + engineeredBy
				+ ", determineModification=" + determineModification + ", isShingles=" + isShingles
				+ ", indicateLayers=" + indicateLayers + ", mpptTrachers=" + mpptTrachers + ", NumberMpptTrachers="
				+ NumberMpptTrachers + ", NumberStringFirstMpptTrachers=" + NumberStringFirstMpptTrachers
				+ ", NumberStringSecondMpptTrachers=" + NumberStringSecondMpptTrachers
				+ ", NumberModuleStringFirstMpptTrachers=" + NumberModuleStringFirstMpptTrachers
				+ ", NumberModuleStringSecondMpptTrachers=" + NumberModuleStringSecondMpptTrachers
				+ ", isTransformless=" + isTransformless + ", numberInputTransformless=" + numberInputTransformless
				+ ", isCombiner=" + isCombiner + ", numberInputCombiner=" + numberInputCombiner + ", overhangArea="
				+ overhangArea + ", roofPitch=" + roofPitch + ", adressIng=" + adressIng + "]";
	}

	
	
	
	
	
	
	
}
