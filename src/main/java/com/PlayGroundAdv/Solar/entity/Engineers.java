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


@Entity
@Table(name = "Engineers")
public class Engineers {

	/**
	 *  CLASS ENTITY
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="Engineers_sequence",
			           sequenceName="Engineers_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Engineers_sequence")  
	private Long id;
	
	@Column(name="COMPANY")
	private String company;
	
	@Column(name="CONTACT")
    private String contact;
	
	@Column(name="ADDRESS")
    private String address;
	
	@Column(name="CITY")
    private String city;
	
	@Column(name="STATE")
    private String state;
	
	@Column(name="ZIP_CODE")
    private String zipCode;
	
	@Column(name="LICENSE_STATE")
    private String licenseState;
	
	@Column(name="LICENSE")
    private String license;
	
	@Column(name="LICENSE_TYPE")
    private String licenseType;
	
	@Column(name="LICENSE_EXPIRATION")
    private String licenseExpiration;
	
	@Column(name="PHONE")
    private String phone;
	
	@Column(name="EMAIL")
    private String email;
	
	@Column(name="UPDATED")
	private String updated;
	
	@Column(name="IS_DELETED")
	private boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	@Override
	public String toString() {
		return "Engineers [id=" + id + ", company=" + company + ", contact=" + contact + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", licenseState=" + licenseState
				+ ", license=" + license + ", licenseType=" + licenseType + ", licenseExpiration=" + licenseExpiration
				+ ", phone=" + phone + ", email=" + email + ", updated=" + updated + ", isDeleted=" + isDeleted
				+ ", hasSuperUserEdit=" + hasSuperUserEdit + ", addDate=" + addDate + ", authentificationEntity="
				+ authentificationEntity + "]";
	}
	
    

    
}
