package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @author Arij
 */
@Entity
@Table(name = "DCCombinerDisconnectEntity" , indexes = {
	       @Index(name = "DCCOMBO_INDX_0",  columnList="MANUFACTURER,MODEL")
	      })
public class DCCombinerDisconnectEntity {
	
	
private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence32", sequenceName = "hibernate_sequence32", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence32")  
	private Long id;
	
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity idOwner;
	
	@Column(name="MANUFACTURER")
	private String manufacturer;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="OCPD")
	private String ocpd;
	
	@Column(name="WEIGHT")
	private String weight;
	
	@Column(name="NEMA_RATING")
	private String nemaRating;
	
	@Column(name="MAX_INPUT")
	private String maxInput;
	
	@Column(name="MAX_CONTI_OUTPUT_CURRENT")
	private String maxContiOutputCurrent;
	
	@Column(name="MAX_OUTPUT_CURRENT")
	private String maxOutputCurrent;
	
	@Column(name="TYPE_DC")
	private String typeDc;
	
	@Column(name="ISDELETED")
	private Boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@Column(name="ERONEOUS_CONTENT")
	private String eroneousContent;
	
	@Column(name="ERONEOUS_CONTENT_OTHER")
	private String eroneousContentOther;
	
	@Column(name="ERONEOUS_DESCRIPTION")
	private String eroneousDescription;
	
	@Column(name="HAS_CORRECTION_REQUEST")
	private Boolean hasCorrectionRequest;
	
	@Column(name="MANUFACTURER_MAPPING_VALUE")
	private String manufacturerMappingValue;
	
	@Column(name="MODEL_MAPPING_VALUE")
	private String modelMappingValue;
	
	@Column(name="DROPDOWNOPTION")
	private String dropdownOption;
	
	@Column(name="LAST_UPDATE")
	private String lastUpdate;
	
	@Column(name="RSD")
	private String rsd;
	
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
	
	
	public AuthentificationEntity getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(AuthentificationEntity firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public AuthentificationEntity getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(AuthentificationEntity secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public AuthentificationEntity getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public AuthentificationEntity getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(AuthentificationEntity verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(Date dateVerification) {
		this.dateVerification = dateVerification;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
	

	public String getRsd() {
		return rsd;
	}

	public void setRsd(String rsd) {
		this.rsd = rsd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaxOutputCurrent() {
		return maxOutputCurrent;
	}

	public void setMaxOutputCurrent(String maxOutputCurrent) {
		this.maxOutputCurrent = maxOutputCurrent;
	}


	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOcpd() {
		return ocpd;
	}

	public void setOcpd(String ocpd) {
		this.ocpd = ocpd;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getNemaRating() {
		return nemaRating;
	}

	public void setNemaRating(String nemaRating) {
		this.nemaRating = nemaRating;
	}

	public String getMaxInput() {
		return maxInput;
	}

	public void setMaxInput(String maxInput) {
		this.maxInput = maxInput;
	}

	public String getMaxContiOutputCurrent() {
		return maxContiOutputCurrent;
	}

	public void setMaxContiOutputCurrent(String maxContiOutputCurrent) {
		this.maxContiOutputCurrent = maxContiOutputCurrent;
	}

	public String getTypeDc() {
		return typeDc;
	}

	public void setTypeDc(String typeDc) {
		this.typeDc = typeDc;
	}

	public AuthentificationEntity getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(AuthentificationEntity idOwner) {
		this.idOwner = idOwner;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDropdownOption() {
		return dropdownOption;
	}

	public void setDropdownOption(String dropdownOption) {
		this.dropdownOption = dropdownOption;
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

	public String getEroneousContent() {
		return eroneousContent;
	}

	public void setEroneousContent(String eroneousContent) {
		this.eroneousContent = eroneousContent;
	}

	public String getEroneousContentOther() {
		return eroneousContentOther;
	}

	public void setEroneousContentOther(String eroneousContentOther) {
		this.eroneousContentOther = eroneousContentOther;
	}

	public String getEroneousDescription() {
		return eroneousDescription;
	}

	public void setEroneousDescription(String eroneousDescription) {
		this.eroneousDescription = eroneousDescription;
	}

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}
	
	public String getManufacturerMappingValue() {
		return manufacturerMappingValue;
	}

	public void setManufacturerMappingValue(String manufacturerMappingValue) {
		this.manufacturerMappingValue = manufacturerMappingValue;
	}

	public String getModelMappingValue() {
		return modelMappingValue;
	}

	public void setModelMappingValue(String modelMappingValue) {
		this.modelMappingValue = modelMappingValue;
	}

	@Override
	public String toString() {
		return "DCCombinerDisconnectEntity [id=" + id + ", idOwner=" + idOwner + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", ocpd=" + ocpd + ", weight=" + weight + ", nemaRating=" + nemaRating
				+ ", maxInput=" + maxInput + ", maxContiOutputCurrent=" + maxContiOutputCurrent + ", maxOutputCurrent="
				+ maxOutputCurrent + ", typeDc=" + typeDc + ", isDeleted=" + isDeleted + ", hasSuperUserEdit="
				+ hasSuperUserEdit + ", addDate=" + addDate + ", eroneousContent=" + eroneousContent
				+ ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription=" + eroneousDescription
				+ ", hasCorrectionRequest=" + hasCorrectionRequest + ", manufacturerMappingValue="
				+ manufacturerMappingValue + ", modelMappingValue=" + modelMappingValue + ", dropdownOption="
				+ dropdownOption + ", lastUpdate=" + lastUpdate + ", rsd=" + rsd + "]";
	}
	
	
}
