package com.PlayGroundAdv.Solar.model;

public class PermitEngineerEntityResult {

	private String applicableEngineering;
	private String name;
	private String email;
	private String mobile;
	private String phone;
	private String licenceNumber;
	private String licenceType;
	private String city;
	private String state;
	private String codePostale;
	private String engineeredBy;
	private Boolean determineModification;
	private Boolean isShingles;
	private String indicateLayers;
	private Boolean mpptTrachers;
	private String NumberMpptTrachers;
	private String NumberStringFirstMpptTrachers2;
	private String NumberStringSecondMpptTrachers2;
	private String NumberModuleStringFirstMpptTrachers2;
	private String NumberModuleStringSecondMpptTrachers2;
	private Boolean isTransformless;
	private String numberInputTransformless2;
	private Boolean isCombiner;
	private String numberInputCombiner2;
	private String overhangArea;
	private String roofPitch;
	private String adressIng;
	// variable format int

	private Integer NumberStringFirstMpptTrachers;
	private Integer NumberStringSecondMpptTrachers;
	private Integer NumberModuleStringFirstMpptTrachers;
	private Integer NumberModuleStringSecondMpptTrachers;
	private Integer numberInputTransformless;
	private Integer numberInputCombiner;

	public PermitEngineerEntityResult() {
		super();
	}

	public PermitEngineerEntityResult(String applicableEngineering, String name, String email, String mobile,
			String phone, String licenceNumber, String licenceType, String city, String state, String codePostale,
			String engineeredBy, Boolean determineModification, Boolean isShingles, String indicateLayers,
			Boolean mpptTrachers, String numberMpptTrachers, String numberStringFirstMpptTrachers2,
			String numberStringSecondMpptTrachers2, String numberModuleStringFirstMpptTrachers2,
			String numberModuleStringSecondMpptTrachers2, Boolean isTransformless, String numberInputTransformless2,
			Boolean isCombiner, String numberInputCombiner2, String overhangArea, String roofPitch, String adressIng) {
		super();
		this.applicableEngineering = applicableEngineering;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.phone = phone;
		this.licenceNumber = licenceNumber;
		this.licenceType = licenceType;
		this.city = city;
		this.state = state;
		this.codePostale = codePostale;
		this.engineeredBy = engineeredBy;
		this.determineModification = determineModification;
		this.isShingles = isShingles;
		this.indicateLayers = indicateLayers;
		this.mpptTrachers = mpptTrachers;
		this.NumberMpptTrachers = numberMpptTrachers;
		this.NumberStringFirstMpptTrachers2 = numberStringFirstMpptTrachers2;
		this.NumberStringSecondMpptTrachers2 = numberStringSecondMpptTrachers2;
		this.NumberModuleStringFirstMpptTrachers2 = numberModuleStringFirstMpptTrachers2;
		this.NumberModuleStringSecondMpptTrachers2 = numberModuleStringSecondMpptTrachers2;
		this.isTransformless = isTransformless;
		this.numberInputTransformless2 = numberInputTransformless2;
		this.isCombiner = isCombiner;
		this.numberInputCombiner2 = numberInputCombiner2;
		this.overhangArea = overhangArea;
		this.roofPitch = roofPitch;
		this.adressIng = adressIng;

		setNumberStringFirstMpptTrachers(numberStringFirstMpptTrachers2);
		setNumberStringSecondMpptTrachers(numberStringSecondMpptTrachers2);
		setNumberModuleStringFirstMpptTrachers(numberModuleStringFirstMpptTrachers2);
		setNumberModuleStringSecondMpptTrachers(numberModuleStringSecondMpptTrachers2);
		setNumberInputTransformless(numberInputTransformless2);
		setNumberInputCombiner(numberInputCombiner2);

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
	public String getNumberStringFirstMpptTrachers2() {
		return NumberStringFirstMpptTrachers2;
	}

	/**
	 * @param numberStringFirstMpptTrachers the numberStringFirstMpptTrachers to set
	 */
	public void setNumberStringFirstMpptTrachers2(String numberStringFirstMpptTrachers2) {
		NumberStringFirstMpptTrachers2 = numberStringFirstMpptTrachers2;
	}

	/**
	 * @return the numberStringSecondMpptTrachers
	 */
	public String getNumberStringSecondMpptTrachers2() {
		return NumberStringSecondMpptTrachers2;
	}

	/**
	 * @param numberStringSecondMpptTrachers the numberStringSecondMpptTrachers to
	 *                                       set
	 */
	public void setNumberStringSecondMpptTrachers2(String numberStringSecondMpptTrachers) {
		NumberStringSecondMpptTrachers2 = numberStringSecondMpptTrachers;
	}

	/**
	 * @return the numberModuleStringFirstMpptTrachers
	 */
	public String getNumberModuleStringFirstMpptTrachers2() {
		return NumberModuleStringFirstMpptTrachers2;
	}

	/**
	 * @param numberModuleStringFirstMpptTrachers the
	 *                                            numberModuleStringFirstMpptTrachers
	 *                                            to set
	 */
	public void setNumberModuleStringFirstMpptTrachers2(String numberModuleStringFirstMpptTrachers) {
		NumberModuleStringFirstMpptTrachers2 = numberModuleStringFirstMpptTrachers;
	}

	/**
	 * @return the numberModuleStringSecondMpptTrachers
	 */
	public String getNumberModuleStringSecondMpptTrachers2() {
		return NumberModuleStringSecondMpptTrachers2;
	}

	/**
	 * @param numberModuleStringSecondMpptTrachers the
	 *                                             numberModuleStringSecondMpptTrachers
	 *                                             to set
	 */
	public void setNumberModuleStringSecondMpptTrachers2(String numberModuleStringSecondMpptTrachers2) {
		NumberModuleStringSecondMpptTrachers2 = numberModuleStringSecondMpptTrachers2;
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
	public String getNumberInputTransformless2() {
		return numberInputTransformless2;
	}

	/**
	 * @param numberInputTransformless the numberInputTransformless to set
	 */
	public void setNumberInputTransformless2(String numberInputTransformless2) {
		this.numberInputTransformless2 = numberInputTransformless2;
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
	public String getNumberInputCombiner2() {
		return numberInputCombiner2;
	}

	/**
	 * @param numberInputCombiner the numberInputCombiner to set
	 */
	public void setNumberInputCombiner2(String numberInputCombiner2) {
		this.numberInputCombiner2 = numberInputCombiner2;
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

	public String getIndicateLayers() {
		return indicateLayers;
	}

	public void setIndicateLayers(String indicateLayers) {
		this.indicateLayers = indicateLayers;
	}

	/**
	 * @return the numberStringFirstMpptTrachers
	 */
	public Integer getNumberStringFirstMpptTrachers() {
		return NumberStringFirstMpptTrachers;
	}

	/**
	 * @param numberStringFirstMpptTrachers the numberStringFirstMpptTrachers to set
	 */
	public void setNumberStringFirstMpptTrachers(String numberStringFirstMpptTrachers) {
		if (numberStringFirstMpptTrachers != null) {
			this.NumberStringFirstMpptTrachers = Integer.parseInt(numberStringFirstMpptTrachers);
		} else {
			this.NumberStringFirstMpptTrachers = null;
		}

	}

	/**
	 * @return the numberStringSecondMpptTrachers
	 */
	public Integer getNumberStringSecondMpptTrachers() {
		return NumberStringSecondMpptTrachers;
	}

	/**
	 * @param numberStringSecondMpptTrachers the numberStringSecondMpptTrachers to
	 *                                       set
	 */
	public void setNumberStringSecondMpptTrachers(String numberStringSecondMpptTrachers) {

		if (numberStringSecondMpptTrachers != null) {
			this.NumberStringSecondMpptTrachers = Integer.parseInt(numberStringSecondMpptTrachers);
		} else {
			this.NumberStringSecondMpptTrachers = null;
		}

	}

	/**
	 * @return the numberModuleStringFirstMpptTrachers
	 */
	public Integer getNumberModuleStringFirstMpptTrachers() {
		return NumberModuleStringFirstMpptTrachers;
	}

	/**
	 * @param numberModuleStringFirstMpptTrachers the
	 *                                            numberModuleStringFirstMpptTrachers
	 *                                            to set
	 */
	public void setNumberModuleStringFirstMpptTrachers(String numberModuleStringFirstMpptTrachers) {

		if (numberModuleStringFirstMpptTrachers != null) {
			this.NumberModuleStringFirstMpptTrachers = Integer.parseInt(numberModuleStringFirstMpptTrachers);
		} else {
			this.NumberModuleStringFirstMpptTrachers = null;
		}

	}

	/**
	 * @return the numberModuleStringSecondMpptTrachers
	 */
	public Integer getNumberModuleStringSecondMpptTrachers() {
		return NumberModuleStringSecondMpptTrachers;
	}

	/**
	 * @param numberModuleStringSecondMpptTrachers the
	 *                                             numberModuleStringSecondMpptTrachers
	 *                                             to set
	 */
	public void setNumberModuleStringSecondMpptTrachers(String numberModuleStringSecondMpptTrachers) {

		if (numberModuleStringSecondMpptTrachers != null) {
			this.NumberModuleStringSecondMpptTrachers = Integer.parseInt(numberModuleStringSecondMpptTrachers);
		} else {
			this.NumberModuleStringSecondMpptTrachers = null;
		}

	}

	/**
	 * @return the numberInputTransformless
	 */
	public Integer getNumberInputTransformless() {
		return numberInputTransformless;
	}

	/**
	 * @param numberInputTransformless the numberInputTransformless to set
	 */
	public void setNumberInputTransformless(String numberInputTransformless) {

		if (numberInputTransformless != null) {
			this.numberInputTransformless = Integer.parseInt(numberInputTransformless);
		} else {
			this.numberInputTransformless = null;
		}

	}

	/**
	 * @return the numberInputCombiner
	 */
	public Integer getNumberInputCombiner() {
		return numberInputCombiner;
	}

	/**
	 * @param numberInputCombiner the numberInputCombiner to set
	 */
	public void setNumberInputCombiner(String numberInputCombiner) {

		if (numberInputCombiner != null) {
			this.numberInputCombiner = Integer.parseInt(numberInputCombiner);
		} else {
			this.numberInputCombiner = null;
		}

	}

	/**
	 * @return the applicableEngineering
	 */
	public String getApplicableEngineering() {
		return applicableEngineering;
	}

	/**
	 * @param applicableEngineering the applicableEngineering to set
	 */
	public void setApplicableEngineering(String applicableEngineering) {
		this.applicableEngineering = applicableEngineering;
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
		return "PermitEngineerEntityResult [applicableEngineering=" + applicableEngineering + ", name=" + name
				+ ", email=" + email + ", mobile=" + mobile + ", phone=" + phone + ", licenceNumber=" + licenceNumber
				+ ", licenceType=" + licenceType + ", city=" + city + ", state=" + state + ", codePostale="
				+ codePostale + ", engineeredBy=" + engineeredBy + ", determineModification=" + determineModification
				+ ", isShingles=" + isShingles + ", indicateLayers=" + indicateLayers + ", mpptTrachers=" + mpptTrachers
				+ ", NumberMpptTrachers=" + NumberMpptTrachers + ", NumberStringFirstMpptTrachers2="
				+ NumberStringFirstMpptTrachers2 + ", NumberStringSecondMpptTrachers2="
				+ NumberStringSecondMpptTrachers2 + ", NumberModuleStringFirstMpptTrachers2="
				+ NumberModuleStringFirstMpptTrachers2 + ", NumberModuleStringSecondMpptTrachers2="
				+ NumberModuleStringSecondMpptTrachers2 + ", isTransformless=" + isTransformless
				+ ", numberInputTransformless2=" + numberInputTransformless2 + ", isCombiner=" + isCombiner
				+ ", numberInputCombiner2=" + numberInputCombiner2 + ", overhangArea=" + overhangArea + ", roofPitch="
				+ roofPitch + ", adressIng=" + adressIng + ", NumberStringFirstMpptTrachers="
				+ NumberStringFirstMpptTrachers + ", NumberStringSecondMpptTrachers=" + NumberStringSecondMpptTrachers
				+ ", NumberModuleStringFirstMpptTrachers=" + NumberModuleStringFirstMpptTrachers
				+ ", NumberModuleStringSecondMpptTrachers=" + NumberModuleStringSecondMpptTrachers
				+ ", numberInputTransformless=" + numberInputTransformless + ", numberInputCombiner="
				+ numberInputCombiner + "]";
	}

}
