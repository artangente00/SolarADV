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
 * @author Administrator
 *
 */
@Entity
@Table(name = "ContractorInformationEntity")
public class ContractorInformationEntity {

	
	@Id
	@SequenceGenerator(name="UserInfo_seq",
			           sequenceName="UserInfo_seq",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserInfo_seq")  
	private Long id;
	
	@JoinColumn(name = "ID_AUTH")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;
	
	@Column(name="DATE")
	private String date;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="SECOND_ADDRESS_LINE")
	private String secondAddressLine;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="POSTAL_CODE")
	private String postalCode;
	
	@Column(name="SAME_MAILING_ADDRESS")
	private Boolean sameMalingAddress;
	
	@Column(name="MAILING_ADDRESS")
	private String mailingAddress;
	
	@Column(name="SECOND_MAILING_ADDRESS")
	private String secondMailingAddress;
	
	@Column(name="MAILING_CITY")
	private String mailingCity;
	
	@Column(name="MAILING_STATE")
	private String mailingState;
	
	@Column(name="MAILING_ZIP_CODE")
	private String mailingZipCode;
	
	@Column (name="COMPANY_PHONE_NUMBER")
	private String compPhoneNum;
	
	@Column (name="CONTACT_OPTIONS")
	private String contactOptions;
	
	@Column (name="CONTACT_OPTIONS_OTHER")
	private String contactOptionsOther;
	
	@Column(name="CONTACT_FIRST_NAME")
	private String contactFirstName;
	
	@Column(name="CONTACT_LAST_NAME")
	private String contactLastName;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;
	
	@Column(name="CONTACT_PHONE")
	private String contactPhone;
	
	@Column(name="CONTACT_ADD_PHONE")
	private String contactAddPhone;
	
	@Column(name="SECOND_CONTACT_FIRST_NAME")
	private String secondContactFirstName;
	
	@Column(name="SECOND_CONTACT_LAST_NAME")
	private String secondContactLastName;

	@Column(name="SECOND_CONTACT_EMAIL")
	private String secondContactEmail;
	
	@Column(name="SECOND_CONTACT_PHONE")
	private String secondContactPhone;
	
	@Column(name="SECOND_CONTACT_ADD_PHONE")
	private String secondContactAddPhone;
	
	@Column(name="INCLUDE_SECOND_CONTACT")
	private Boolean includeSecondContact;
	
	@Column(name="THIRD_CONTACT")
	private String thirdContact;

	@Column(name="THIRD_CONTACT_EMAIL")
	private String thirdContactEmail;
	
	@Column(name="THIRD_CONTACT_PHONE")
	private String thirdContactPhone;
	
	@Column(name="THIRD_CONTACT_ADD_PHONE")
	private String thirdContactAddPhone;
	
	@Column(name="INCLUDE_THIRD_CONTACT")
	private Boolean includeThirdContact;
	
	@Column(name="BUSINESS_PHONE")
	private String businessPhone;
	
	@Column(name="OTHER_PHONE")
	private String otherPhone;
	
	@Column(name="DESIGN_BY")
	private String designBy;
	
	@Column(name="LICENSE_NUMBER")
	private String licenseNumber;
	
	@Column(name="LICENSE_EXPIRATION")
	private String licenseExpiration;
	
	@Column(name="CONTRACTOR_LIC")
	private Boolean contractorLic;
	
	
	@Column(name="QUALIFYING_INDIVIDUAL")
	private String qualifyingIndividual;
	
	
	@Column(name="ADDITIONAL_QUALIFYING")
	private String additionalQualifying;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="INCLUDE_SECOND_CONTACT_ONLY")
	private Boolean includeSecondContactOnly;
	
	@Column(name="INCLUDE_SECOND_CONTACT_WHEN")
	private Boolean includeSecondContactWhen;
	
	@Column(name="INCLUDE_THIRD_CONTACT_ONLY")
	private Boolean includeThirdContactOnly;
	
	@Column(name="INCLUDE_THIRD_CONTACT_WHEN")
	private Boolean includeThirdContactWhen;
	
	@Column(name="DESIGNBY_OTHER")
	private String designByOther;
	
	@Column(name="CONTRACTOR_LIC_C10")
	private Boolean contractorLicC10;
	
	@Column(name="CONTRACTOR_LIC_B")
	private Boolean contractorLicB;
	
	@Column(name="QUALIFYING_INDIVIDUAL_OTHER")
	private String qualifyingIndividualOther;
	
	@Column(name="ADDITIONAL_QUALIFYINF_OTHER")
	private String additionalQualifyingOther;
	
	@Column(name="CONTRACTOR_LICENCE_STATE")
	private String contractorLicenceState;
	
	
	@Column(name="IS_PROJECT_ADD_INCLUD")
	private Boolean isProjectAddInclud;
	
	@Column(name="LAST_NAME_CONTACT")
	private String lastNameContact;
	
	public ContractorInformationEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}

	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecondAddressLine() {
		return secondAddressLine;
	}

	public void setSecondAddressLine(String secondAddressLine) {
		this.secondAddressLine = secondAddressLine;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Boolean getSameMalingAddress() {
		return sameMalingAddress;
	}

	public void setSameMalingAddress(Boolean sameMalingAddress) {
		this.sameMalingAddress = sameMalingAddress;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getSecondMailingAddress() {
		return secondMailingAddress;
	}

	public void setSecondMailingAddress(String secondMailingAddress) {
		this.secondMailingAddress = secondMailingAddress;
	}

	public String getMailingCity() {
		return mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingState() {
		return mailingState;
	}

	public void setMailingState(String mailingState) {
		this.mailingState = mailingState;
	}

	public String getMailingZipCode() {
		return mailingZipCode;
	}

	public void setMailingZipCode(String mailingZipCode) {
		this.mailingZipCode = mailingZipCode;
	}

	public String getCompPhoneNum() {
		return compPhoneNum;
	}

	public void setCompPhoneNum(String compPhoneNum) {
		this.compPhoneNum = compPhoneNum;
	}

	public String getContactOptions() {
		return contactOptions;
	}

	public void setContactOptions(String contactOptions) {
		this.contactOptions = contactOptions;
	}

	public String getContactOptionsOther() {
		return contactOptionsOther;
	}

	public void setContactOptionsOther(String contactOptionsOther) {
		this.contactOptionsOther = contactOptionsOther;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactAddPhone() {
		return contactAddPhone;
	}

	public void setContactAddPhone(String contactAddPhone) {
		this.contactAddPhone = contactAddPhone;
	}

	public String getSecondContactFirstName() {
		return secondContactFirstName;
	}

	public void setSecondContactFirstName(String secondContactFirstName) {
		this.secondContactFirstName = secondContactFirstName;
	}

	public String getSecondContactLastName() {
		return secondContactLastName;
	}

	public void setSecondContactLastName(String secondContactLastName) {
		this.secondContactLastName = secondContactLastName;
	}

	public String getSecondContactEmail() {
		return secondContactEmail;
	}

	public void setSecondContactEmail(String secondContactEmail) {
		this.secondContactEmail = secondContactEmail;
	}

	public String getSecondContactPhone() {
		return secondContactPhone;
	}

	public void setSecondContactPhone(String secondContactPhone) {
		this.secondContactPhone = secondContactPhone;
	}

	public String getSecondContactAddPhone() {
		return secondContactAddPhone;
	}

	public void setSecondContactAddPhone(String secondContactAddPhone) {
		this.secondContactAddPhone = secondContactAddPhone;
	}

	public Boolean getIncludeSecondContact() {
		return includeSecondContact;
	}

	public void setIncludeSecondContact(Boolean includeSecondContact) {
		this.includeSecondContact = includeSecondContact;
	}

	public String getThirdContact() {
		return thirdContact;
	}

	public void setThirdContact(String thirdContact) {
		this.thirdContact = thirdContact;
	}

	public String getThirdContactEmail() {
		return thirdContactEmail;
	}

	public void setThirdContactEmail(String thirdContactEmail) {
		this.thirdContactEmail = thirdContactEmail;
	}

	public String getThirdContactPhone() {
		return thirdContactPhone;
	}

	public void setThirdContactPhone(String thirdContactPhone) {
		this.thirdContactPhone = thirdContactPhone;
	}

	public String getThirdContactAddPhone() {
		return thirdContactAddPhone;
	}

	public void setThirdContactAddPhone(String thirdContactAddPhone) {
		this.thirdContactAddPhone = thirdContactAddPhone;
	}

	public Boolean getIncludeThirdContact() {
		return includeThirdContact;
	}

	public void setIncludeThirdContact(Boolean includeThirdContact) {
		this.includeThirdContact = includeThirdContact;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getDesignBy() {
		return designBy;
	}

	public void setDesignBy(String designBy) {
		this.designBy = designBy;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public Boolean getContractorLic() {
		return contractorLic;
	}

	public void setContractorLic(Boolean contractorLic) {
		this.contractorLic = contractorLic;
	}

	public String getQualifyingIndividual() {
		return qualifyingIndividual;
	}

	public void setQualifyingIndividual(String qualifyingIndividual) {
		this.qualifyingIndividual = qualifyingIndividual;
	}

	public String getAdditionalQualifying() {
		return additionalQualifying;
	}

	public void setAdditionalQualifying(String additionalQualifying) {
		this.additionalQualifying = additionalQualifying;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getIncludeSecondContactOnly() {
		return includeSecondContactOnly;
	}

	public void setIncludeSecondContactOnly(Boolean includeSecondContactOnly) {
		this.includeSecondContactOnly = includeSecondContactOnly;
	}

	public Boolean getIncludeSecondContactWhen() {
		return includeSecondContactWhen;
	}

	public void setIncludeSecondContactWhen(Boolean includeSecondContactWhen) {
		this.includeSecondContactWhen = includeSecondContactWhen;
	}

	public Boolean getIncludeThirdContactOnly() {
		return includeThirdContactOnly;
	}

	public void setIncludeThirdContactOnly(Boolean includeThirdContactOnly) {
		this.includeThirdContactOnly = includeThirdContactOnly;
	}

	public Boolean getIncludeThirdContactWhen() {
		return includeThirdContactWhen;
	}

	public void setIncludeThirdContactWhen(Boolean includeThirdContactWhen) {
		this.includeThirdContactWhen = includeThirdContactWhen;
	}

	public String getDesignByOther() {
		return designByOther;
	}

	public void setDesignByOther(String designByOther) {
		this.designByOther = designByOther;
	}

	public Boolean getContractorLicC10() {
		return contractorLicC10;
	}

	public void setContractorLicC10(Boolean contractorLicC10) {
		this.contractorLicC10 = contractorLicC10;
	}

	public Boolean getContractorLicB() {
		return contractorLicB;
	}

	public void setContractorLicB(Boolean contractorLicB) {
		this.contractorLicB = contractorLicB;
	}

	public String getQualifyingIndividualOther() {
		return qualifyingIndividualOther;
	}

	public void setQualifyingIndividualOther(String qualifyingIndividualOther) {
		this.qualifyingIndividualOther = qualifyingIndividualOther;
	}

	public String getAdditionalQualifyingOther() {
		return additionalQualifyingOther;
	}

	public void setAdditionalQualifyingOther(String additionalQualifyingOther) {
		this.additionalQualifyingOther = additionalQualifyingOther;
	}

	public String getContractorLicenceState() {
		return contractorLicenceState;
	}

	public void setContractorLicenceState(String contractorLicenceState) {
		this.contractorLicenceState = contractorLicenceState;
	}

	public Boolean getIsProjectAddInclud() {
		return isProjectAddInclud;
	}

	public void setIsProjectAddInclud(Boolean isProjectAddInclud) {
		this.isProjectAddInclud = isProjectAddInclud;
	}

	public String getLastNameContact() {
		return lastNameContact;
	}

	public void setLastNameContact(String lastNameContact) {
		this.lastNameContact = lastNameContact;
	}
	
	
   
}
