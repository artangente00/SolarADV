package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/*
 * @author nader
 */
@Entity
@Table(name = "EEngineerEntity")
public class EngineerEntity {

	/**
	 *  Permit Engineer Entity
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence11", sequenceName = "hibernate_sequence11", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence11")  
	private Long id;
	

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
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="CODE_POSTALE")
	private String codePostale;
	
	@Column(name="LICENSE_STATE")
	private String licenseState;

	@Column(name="LICENSE_EXPIRATION")
	private String licenseExpiration;
	
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	public String getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
